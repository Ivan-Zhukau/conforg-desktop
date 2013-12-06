package net.ostis.confman.model.datastore.util;

import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;

public class EntityComparer {

    public EntityComparer() {

        super();
    }

    public boolean comparePersons(final Person person,
            final net.ostis.confman.model.entity.Person storedPerson) {

        if (person.getFirstName() == null || person.getPatronymic() == null
                || person.getSurname() == null
                || storedPerson.getFirstName() == null
                || storedPerson.getPatronymic() == null
                || storedPerson.getSurname() == null) {
            return false;
        }
        return storedPerson.getFirstName().equals(person.getFirstName())
                && storedPerson.getPatronymic().equals(person.getPatronymic())
                && storedPerson.getSurname().equals(person.getSurname());
    }

    public boolean compareParticipants(final Participant participant,
            final net.ostis.confman.model.entity.Participant storedParticipant) {

        // TODO Auto-generated method stub
        return false;
    }
}
