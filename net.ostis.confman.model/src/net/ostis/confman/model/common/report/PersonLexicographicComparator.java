package net.ostis.confman.model.common.report;

import java.util.Comparator;

import net.ostis.confman.services.common.model.Person;

public class PersonLexicographicComparator implements Comparator<Person> {

    @Override
    public int compare(final Person a, final Person b) {

        return a.getSurname().compareToIgnoreCase(b.getSurname());
    }

}
