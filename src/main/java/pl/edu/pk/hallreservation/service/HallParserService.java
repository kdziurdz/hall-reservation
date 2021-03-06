package pl.edu.pk.hallreservation.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.model.DayOfWeek;
import pl.edu.pk.hallreservation.model.hall.Hall;
import pl.edu.pk.hallreservation.model.hall.Lecture;
import pl.edu.pk.hallreservation.model.hall.Reservation;
import pl.edu.pk.hallreservation.repository.HallRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
public class HallParserService {

    private static final String HALL_URL = "http://www.mech.pk.edu.pl/~podzial/stacjonarne/html/plany/s%d.html";
    private static final String ODD_POLISH = "nieparzyste";
    private static final String TITLE_POLISH_CLASS_NAME = "tytulnapis";
    private static final String TABLE_POLISH_CLASS_NAME = "tabela";
    private static final String TBODY_TAG = "tbody";
    private static final String PUBLIC_HALL_POLISH = "ogólnodostępna";

    private final DaysOfWeekService daysOfWeekService;
    private final HallRepository hallRepository;

    @Autowired
    public HallParserService(DaysOfWeekService daysOfWeekService, HallRepository hallRepository) {
        this.daysOfWeekService = daysOfWeekService;
        this.hallRepository = hallRepository;
    }

//    @PostConstruct
    public void refreshHallsClasses() {
        try {
            Map<String, Hall> halls = new HashMap<>();
            int hallUrlNumber = 2; // intended index start
            do {
                Document doc = Jsoup.connect(String.format(HALL_URL, hallUrlNumber)).get();
                if(doc.title().contains(PUBLIC_HALL_POLISH)) {
                    Hall hall = fetchSingleHallData(doc);

                    if(!halls.containsKey(hall.getName())){
                        halls.put(hall.getName(), hall);
                    } else {
                        mergeHalls(halls.get(hall.getName()), hall); // merge even with odd
                    }
                    hallUrlNumber++;
                } else {
                    break;
                }
            } while(true);
            persistHalls(halls);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Hall fetchSingleHallData(Document doc) throws IOException{
        String title = doc.getElementsByClass(TITLE_POLISH_CLASS_NAME).first().text();
        String hallName = getHallName(title);
        Set<Lecture> lectures = new HashSet<>();

        // check if has word "odd" in title to decide if week type is even
        boolean isEven = hasEvenInName(title);


        // get table with schedule info
        Element scheduleTableBody = doc.getElementsByClass(TABLE_POLISH_CLASS_NAME)
                .first().getElementsByTag(TBODY_TAG).first();


        Elements tableRows = scheduleTableBody.children();

        // iterate over all rows. Starts with "1" due to header is in fact in tbody tag " nr Poniedzialek Wtorek ..."
        for (int i = 1; i < tableRows.size(); i++) {
            Elements tableCells = tableRows.get(i).children();

            // first table cell contains info about lesson number
            // second tells about lesson duration
            Integer lessonNumber = Integer.valueOf(tableCells.get(0).text());

            // starts with 2 due to omit lesson number and duration table cells
            for (int j = 2; j < tableCells.size(); j++) {

                // j - 1 because j index has offset to real day of week number (MONDAY = 1)
                lectures.add(collectSingleLessonData(lessonNumber, tableCells.get(j).children().size() == 0,
                        isEven, daysOfWeekService.getByCreds(j - 1)));
            }
        }

        return new Hall(hallName, lectures, new HashSet<>());
    }

    private void persistHalls(Map<String, Hall> halls) {
        hallRepository.save(halls.values());
    }

    private Hall mergeHalls(Hall hall1, Hall hall2){
        hall1.getLectures().addAll(hall2.getLectures());
        return hall1;
    }

    private Lecture collectSingleLessonData(int lessonNumber, boolean isFree, boolean isEven,
                                            DayOfWeek dayOfWeek) {
        return new Lecture(lessonNumber, isEven, isFree, dayOfWeek);

    }

    private String getHallName(String title) {
        return title.substring(0, title.indexOf("-"));
    }

    private boolean hasEvenInName(String name) {
        return !name.toLowerCase().contains(ODD_POLISH);
    }
}
