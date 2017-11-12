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

    private static String SALA_URL = "http://www.mech.pk.edu.pl/~podzial/stacjonarne/html/plany/s2.html";


    private final DaysOfWeekService daysOfWeekService;

    @Autowired
    public HallService(HallService hallService, DaysOfWeekService daysOfWeekService) {
        this.daysOfWeekService = daysOfWeekService;
    }

    public void refreshHallsClasses() {

        try {
            Document doc = Jsoup.connect(SALA_URL).get();
            String title = doc.getElementsByClass("tytulnapis").first().text();
            boolean isEven = hasEvenInName(title);
            Elements foundTables = doc.getElementsByClass("tabela");

            Element scheduleTableBody = foundTables.first().getElementsByTag("tbody").first();

            Elements tableRows = scheduleTableBody.children();

            for (int i = 1; i < tableRows.size(); i++) {
                Elements tableCells = tableRows.get(i).children();
                Integer lessonNumber = Integer.valueOf(tableCells.get(0).text());
                for (int j = 2; j < tableCells.size(); j++) {
                    collectSingleLessonData(title, lessonNumber, tableCells.get(j).children().size() == 0,
                            daysOfWeekService.getByCreds(j - 1, isEven));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void collectSingleLessonData(String hallName, int lessonNumber, boolean isFree, DayOfWeek dayOfWeek) {
        System.out.println(String.format("Sala %s w %d godzinie lekcyjnej jest %s. Dzien tygodnia to: %s %s", hallName, lessonNumber,
                isFree ? "wolna" : "zajeta", dayOfWeek.isEven() ? "parzysty" : "nieparzysty", dayOfWeek.getNumberOfDay()));

    }

    private boolean hasEvenInName(String name) {
        return !name.toLowerCase().contains("nieparzyste");
    }
}
