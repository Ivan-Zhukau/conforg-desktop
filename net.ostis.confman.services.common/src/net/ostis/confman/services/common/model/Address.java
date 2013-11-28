package net.ostis.confman.services.common.model;

public class Address {

    private String country;

    private String city;

    private String street;

    private String houseNumber;

    public Address() {

        super();
    }

    public String getCountry() {

        return this.country;
    }

    public void setCountry(final String country) {

        this.country = country;
    }

    public String getHouseNumber() {

        return this.houseNumber;
    }

    public void setHouseNumber(final String houseNumber) {

        this.houseNumber = houseNumber;
    }

    public String getStreet() {

        return this.street;
    }

    public void setStreet(final String street) {

        this.street = street;
    }

    public String getCity() {

        return this.city;
    }

    public void setCity(final String city) {

        this.city = city;
    }

}
