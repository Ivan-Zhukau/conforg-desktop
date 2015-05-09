package net.ostis.confman.model.entity.scmemory;

public class ContactInformation extends BaseEntity {

    private String email;

    private String phone;

    public ContactInformation() {

        super();
    }

    public ContactInformation(String email, String phone) {

        super();
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {

        return this.email;
    }

    public void setEmail(final String email) {

        this.email = email;
    }

    public String getPhone() {

        return this.phone;
    }

    public void setPhone(final String phone) {

        this.phone = phone;
    }
}