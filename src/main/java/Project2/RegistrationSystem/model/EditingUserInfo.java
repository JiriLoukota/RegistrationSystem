package Project2.RegistrationSystem.model;

public class EditingUserInfo {
    private int id;
    private String name, surname;

    public EditingUserInfo(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    //region Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    //endregion
}
