package pl.edu.pk.hallreservation.service.hall.dto;

public class HallDTO {
    private String name;
    private Long id;

    public HallDTO(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public HallDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
