package pl.edu.pk.hallreservation.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.model.DayOfWeek;

import java.io.IOException;

@Service
public class HallService {

    private static final String HALL_URL = "http://www.mech.pk.edu.pl/~podzial/stacjonarne/html/plany/s%d.html";
    private static final String ODD_POLISH = "nieparzyste";
    private static final String TITLE_POLISH_CLASS_NAME = "tytulnapis";
    private static final String TABLE_POLISH_CLASS_NAME = "tabela";
    private static final String TBODY_TAG = "tbody";
    private static final String PUBLIC_HALL_POLISH = "ogólnodostępna";

    private final DaysOfWeekService daysOfWeekService;

    @Autowired
    public HallService(DaysOfWeekService daysOfWeekService) {
        this.daysOfWeekService = daysOfWeekService;
    }

    public void refreshHallsClasses() {
        try {
            int hallUrlNumber = 2; // intended index start
            do {
                Document doc = Jsoup.connect(String.format(HALL_URL, hallUrlNumber)).get();
                if(doc.title().contains(PUBLIC_HALL_POLISH)) {
                    fetchSingleHallData(doc);
                    hallUrlNumber++;
                } else {
                    break;
                }
            } while(true);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void fetchSingleHallData(Document doc) throws IOException{
        String title = doc.getElementsByClass(TITLE_POLISH_CLASS_NAME).first().text();

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
                collectSingleLessonData(title, lessonNumber, tableCells.get(j).children().size() == 0,
                        daysOfWeekService.getByCreds(j - 1, isEven));
            }
        }
    }

    private void collectSingleLessonData(String hallName, int lessonNumber, boolean isFree, DayOfWeek dayOfWeek) {
        System.out.println(String.format("Sala %s w %d godzinie lekcyjnej jest %s. Dzien tygodnia to: %s %s", hallName, lessonNumber,
                isFree ? "wolna" : "zajeta", dayOfWeek.isEven() ? "parzysty" : "nieparzysty", dayOfWeek.getNumberOfDay()));

    }

    private boolean hasEvenInName(String name) {
        return !name.toLowerCase().contains(ODD_POLISH);
    }
}
