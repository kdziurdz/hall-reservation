package pl.edu.pk.hallreservation.controller.user.vm;

public class UserVM extends UserName {
    private Long id;

    public UserVM(String firstName, String lastName, Long id) {
        super(firstName, lastName);
        this.id = id;
    }

    public UserVM() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
