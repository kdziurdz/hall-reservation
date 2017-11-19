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

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

    public void refreshHallsClasses() {
        try {
            int hallUrlNumber = 2; // intended index start
            do {
                Document doc = Jsoup.connect(String.format(HALL_URL, hallUrlNumber)).get();
                if(doc.title().contains(PUBLIC_HALL_POLISH)) {
                    hallRepository.save(fetchSingleHallData(doc));
                    hallUrlNumber++;
                } else {
                    break;
                }
            } while(true);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Hall fetchSingleHallData(Document doc) throws IOException{
        String title = doc.getElementsByClass(TITLE_POLISH_CLASS_NAME).first().text();
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
                lectures.add(collectSingleLessonData(title, lessonNumber, tableCells.get(j).children().size() == 0,
                        daysOfWeekService.getByCreds(j - 1, isEven)));
            }
        }

        return new Hall(title, lectures, new HashSet<>());
    }

    private Lecture collectSingleLessonData(String hallName, int lessonNumber, boolean isFree, DayOfWeek dayOfWeek) {
        System.out.println(String.format("Sala %s w %d godzinie lekcyjnej jest %s. Dzien tygodnia to: %s %s", hallName, lessonNumber,
                isFree ? "wolna" : "zajeta", dayOfWeek.isEven() ? "parzysty" : "nieparzysty", dayOfWeek.getNumberOfDay()));
        return new Lecture(lessonNumber, dayOfWeek.isEven(), isFree, dayOfWeek); //TODO delete day of week

    }

    private boolean hasEvenInName(String name) {
        return !name.toLowerCase().contains(ODD_POLISH);
    }
}
