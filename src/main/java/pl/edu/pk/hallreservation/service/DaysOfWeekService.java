package pl.edu.pk.hallreservation.service;

import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.model.DayOfWeek;

@Service
public class DaysOfWeekService {
    public DayOfWeek getByCreds(int numberOfDay, boolean isEven) {
        switch (numberOfDay) {
            case 1: {
                return isEven ? DayOfWeek.MONDAY_EVEN : DayOfWeek.MONDAY_ODD;
            }
            case 2: {
                return isEven ? DayOfWeek.TUESDAY_EVEN : DayOfWeek.TUESDAY_ODD;
            }
            case 3: {
                return isEven ? DayOfWeek.WEDNESDAY_EVEN : DayOfWeek.WEDNESDAY_ODD;
            }
            case 4: {
                return isEven ? DayOfWeek.THURSDAY_EVEN : DayOfWeek.THURSDAY_ODD;
            }
            case 5: {
                return isEven ? DayOfWeek.FRIDAY_EVEN : DayOfWeek.FRIDAY_ODD;
            }
            default: {
                throw new IllegalArgumentException("Cannot match proper day of week with number: " + numberOfDay);
            }
        }
    }
}
