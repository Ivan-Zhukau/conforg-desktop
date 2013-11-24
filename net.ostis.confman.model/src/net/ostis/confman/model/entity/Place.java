package net.ostis.confman.model.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement(name = "place")
public class Place {

    private String country;

    private String city;

    public Place() {

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
}
