package pl.edu.pk.hallreservation.model;

public enum DayOfWeek {
    MONDAY_ODD(1, false),
    TUESDAY_ODD(2, false),
    WEDNESDAY_ODD(3, false),
    THURSDAY_ODD(4, false),
    FRIDAY_ODD(5, false),
    MONDAY_EVEN(1, true),
    TUESDAY_EVEN(2, true),
    WEDNESDAY_EVEN(3, true),
    THURSDAY_EVEN(4, true),
    FRIDAY_EVEN(5, true);

    int numberOfDay;
    boolean isEven;

    DayOfWeek(int numberOfDay, boolean isEven) {
        this.numberOfDay = numberOfDay;
        this.isEven = isEven;
    }

    public int getNumberOfDay() {
        return numberOfDay;
    }

    public boolean isEven() {
        return isEven;
    }
}
