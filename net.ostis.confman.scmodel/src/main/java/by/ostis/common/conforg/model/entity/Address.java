package by.ostis.common.conforg.model.entity;


public class Address extends BaseEntity {

    private String country;
    private String city;
    private String street;
    private String houseNumber;

    public Address() {
        super();
    }

    public Address(String country, String city, String street, String houseNumber) {
        super();
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
