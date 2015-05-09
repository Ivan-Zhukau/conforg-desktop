package net.ostis.confman.model.entity.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contacts")
public class ContactInformation {

    private String email;

    private String phone;

    public ContactInformation() {

        super();
    }

    @XmlElement
    public String getEmail() {

        return this.email;
    }

    public void setEmail(final String email) {

        this.email = email;
    }

    @XmlElement
    public String getPhone() {

        return this.phone;
    }

    public void setPhone(final String phone) {

        this.phone = phone;
    }
}