package net.ostis.confman.model.registrationform;

public class ContactInformation {

    private String country;

    private String city;

    private String eMail;

    private String contactPhoneNumber;

    public ContactInformation() {

        super();
    }

    public String getCity() {

        return this.city;
    }

    public String getContactPhoneNumber() {

        return this.contactPhoneNumber;
    }

    public String getCountry() {

        return this.country;
    }

    public String geteMail() {

        return this.eMail;
    }

    public void setCity(final String city) {

        this.city = city;
    }

    public void setContactPhoneNumber(final String contactPhoneNumber) {

        this.contactPhoneNumber = contactPhoneNumber;
    }

    public void setCountry(final String country) {

        this.country = country;
    }

    public void seteMail(final String eMail) {

        this.eMail = eMail;
    }
}
