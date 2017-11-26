package pl.edu.pk.hallreservation.controller.hall.vm;

public class HallVM {
    private String name;
    private Long id;

    public HallVM(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public HallVM() {
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
