package net.ostis.confman.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
public class Persons {

    private List<Person> persons;

    public Persons() {

        super();
        persons = new ArrayList<Person>();
    }

    @XmlElement(name = "person")
    public List<Person> getPersons() {

        return this.persons;
    }

    public void setPersons(final List<Person> persons) {

        this.persons = persons;
    }

}
