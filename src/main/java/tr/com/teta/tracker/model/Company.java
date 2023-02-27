package tr.com.teta.tracker.model;

import java.util.Objects;

public class Company {
    private int id;
    private String company;
    private String phone;
    private String address;

    public Company() {

    }

    public Company(int id) {
        this.id = id;
    }

    public Company(int id, String company) {
        this.id = id;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format(
                "id: %s, company: %s, phone: %s, address: %s",
                id, company, phone, address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Company company1 = (Company) o;
        return id == company1.id && Objects.equals(company, company1.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company);
    }
}
