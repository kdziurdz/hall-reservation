package pl.edu.pk.hallreservation.controller.reservation;


public enum ReservationStatus {

    FUTURE("future"),
    CANCELLED("cancelled"),
    PAST("past");

    private String status;

    ReservationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
