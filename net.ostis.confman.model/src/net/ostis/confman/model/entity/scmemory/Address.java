package net.ostis.confman.model.entity.scmemory;

public class Address extends BaseEntity {

    private String country;

    private String city;

    private String street;

    private String houseNumber;

    public Address() {

        super();
    }
    
    

    public Address(String country, String city, String street,
            String houseNumber) {

        super();
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }



    public String getCountry() {

        return this.country;
    }

    public void setCountry(final String country) {

        this.country = country;
    }

    public String getCity() {

        return this.city;
    }

    public void setCity(final String city) {

        this.city = city;
    }

    public String getStreet() {

        return this.street;
    }

    public void setStreet(final String street) {

        this.street = street;
    }

    public String getHouseNumber() {

        return this.houseNumber;
    }

    public void setHouseNumber(final String houseNumber) {

        this.houseNumber = houseNumber;
    }
}