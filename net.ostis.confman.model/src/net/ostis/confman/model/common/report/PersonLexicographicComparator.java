package net.ostis.confman.model.common.report;

import java.util.Comparator;

import net.ostis.confman.services.common.model.Person;

public class PersonLexicographicComparator implements Comparator<Person> {

    @Override
    public int compare(final Person a, final Person b) {

        System.out.println(a.getSurname() + " " + b.getSurname());

        if (a.getSurname() != null && b.getSurname() == null) {
            return 1;
        } else if (a.getSurname() == null && b.getSurname() != null) {
            return -1;
        } else if (a.getSurname() == null && b.getSurname() == null) {
            return 0;
        } else {
            return a.getSurname().compareToIgnoreCase(b.getSurname());
        }
    }

}
