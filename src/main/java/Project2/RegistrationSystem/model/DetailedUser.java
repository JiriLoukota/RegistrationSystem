package Project2.RegistrationSystem.model;

import java.util.UUID;

public class DetailedUser {
    private String name, surname, personId;
    private UUID uuid;
    private int id;

    public DetailedUser(String name, String surname, String personId, UUID uuid, int id) {
        this.name = name;
        this.surname = surname;
        this.personId = personId;
        this.uuid = uuid;
        this.id = id;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //endregion
}
