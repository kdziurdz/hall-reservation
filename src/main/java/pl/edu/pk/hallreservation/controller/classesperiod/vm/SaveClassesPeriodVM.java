package pl.edu.pk.hallreservation.controller.classesperiod.vm;

import java.time.LocalDate;

public class SaveClassesPeriodVM {
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public SaveClassesPeriodVM(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public SaveClassesPeriodVM() {
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
