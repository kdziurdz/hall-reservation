package pl.edu.pk.hallreservation.service;

import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.model.DayOfWeek;

@Service
public class DaysOfWeekService {
    public DayOfWeek getByCreds(int numberOfDay) {
        switch (numberOfDay) {
            case 1: {
                return DayOfWeek.MONDAY;
            }
            case 2: {
                return DayOfWeek.TUESDAY;
            }
            case 3: {
                return DayOfWeek.WEDNESDAY;
            }
            case 4: {
                return DayOfWeek.THURSDAY;
            }
            case 5: {
                return DayOfWeek.FRIDAY;
            }
            default: {
                throw new IllegalArgumentException("Cannot match proper day of week with number: " + numberOfDay);
            }
        }
    }
}
