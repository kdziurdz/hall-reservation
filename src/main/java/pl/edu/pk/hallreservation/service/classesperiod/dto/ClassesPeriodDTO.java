package pl.edu.pk.hallreservation.service.classesperiod.dto;

import pl.edu.pk.hallreservation.controller.classesperiod.vm.SaveClassesPeriodVM;

import java.time.LocalDate;

public class ClassesPeriodDTO extends SaveClassesPeriodVM{
    private Long id;

    public ClassesPeriodDTO(LocalDate dateFrom, LocalDate dateTo, Long id) {
        super(dateFrom, dateTo);
        this.id = id;
    }

    public ClassesPeriodDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
