package tr.com.teta.tracker.model;

import java.util.Objects;

public class Subscriber {
    private int id;
    private String name;
    private String surname;
    private String subscriberNumber;
    private String plateNumber;
    private int companyId;

    public Subscriber() {

    }

    public Subscriber(String name, String surname, String subscriberNumber, String plateNumber, int companyId) {
        this.name = name;
        this.surname = surname;
        this.subscriberNumber = subscriberNumber;
        this.plateNumber = plateNumber;
        this.companyId = companyId;
    }

    public Subscriber(int id) {
        this.id = id;
    }

    public Subscriber(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subscriber(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Subscriber(int id, String name, String surname, String subscriberNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.subscriberNumber = subscriberNumber;
    }

    public Subscriber(int id, String name, String surname, String subscriberNumber, String plateNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.subscriberNumber = subscriberNumber;
        this.plateNumber = plateNumber;
    }

    public Subscriber(int id, String name, String surname, String subscriberNumber, String plateNumber, int companyId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.subscriberNumber = subscriberNumber;
        this.plateNumber = plateNumber;
        this.companyId = companyId;
    }

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

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return String.format(
                "id=%s, name=%s, surname=%s, subscriberNumber=%s, plateNumber=%s, companyId=%s",
                id, name, surname, subscriberNumber, plateNumber, companyId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subscriber that = (Subscriber) o;
        return id == that.id && Objects.equals(subscriberNumber, that.subscriberNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subscriberNumber);
    }
}