package pl.edu.pk.hallreservation.controller.user.vm;


import javax.validation.constraints.NotNull;

public abstract class UserName  {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public UserName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserName() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
