package pl.edu.pk.hallreservation.controller.admin.vm;

import java.time.LocalDate;

public class ClassesPeriodVM extends SaveClassesPeriodVM{
    private Long id;

    public ClassesPeriodVM(LocalDate dateFrom, LocalDate dateTo, Long id) {
        super(dateFrom, dateTo);
        this.id = id;
    }

    public ClassesPeriodVM() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
