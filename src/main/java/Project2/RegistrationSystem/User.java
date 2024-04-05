package Project2.RegistrationSystem;

import java.util.UUID;

public class User {
    private String  name, surname, personID;
    private UUID uuid;

    public User(String name, String surname, String personID) {
        this.name = name;
        this.surname = surname;
        this.personID = personID;
    }

    //region Getters and setters

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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


    //endregion
}
