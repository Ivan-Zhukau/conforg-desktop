package net.ostis.confman.model.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class Address {

    private String country;

    private String city;

    private String street;

    private String houseNumber;

    public Address() {

        super();
    }

    @XmlElement
    public String getCountry() {

        return this.country;
    }

    public void setCountry(final String country) {

        this.country = country;
    }

    @XmlElement
    public String getCity() {

        return this.city;
    }

    public void setCity(final String city) {

        this.city = city;
    }

    @XmlElement
    public String getStreet() {

        return this.street;
    }

    public void setStreet(final String street) {

        this.street = street;
    }

    @XmlElement(name = "housenumber")
    public String getHouseNumber() {

        return this.houseNumber;
    }

    public void setHouseNumber(final String houseNumber) {

        this.houseNumber = houseNumber;
    }
}