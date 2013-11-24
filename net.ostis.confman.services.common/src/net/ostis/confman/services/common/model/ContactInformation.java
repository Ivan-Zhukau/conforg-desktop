package net.ostis.confman.services.common.model;

public class ContactInformation {

    private String eMail;

    private String contactPhoneNumber;

    public ContactInformation() {

        super();
    }

    public String getContactPhoneNumber() {

        return this.contactPhoneNumber;
    }

    public String geteMail() {

        return this.eMail;
    }

    public void setContactPhoneNumber(final String contactPhoneNumber) {

        this.contactPhoneNumber = contactPhoneNumber;
    }

    public void seteMail(final String eMail) {

        this.eMail = eMail;
    }
}
