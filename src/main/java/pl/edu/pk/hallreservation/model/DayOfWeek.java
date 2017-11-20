package pl.edu.pk.hallreservation.model;

public enum DayOfWeek {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5);

    int numberOfDay;

    DayOfWeek(int numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    public int getNumberOfDay() {
        return numberOfDay;
    }
}
