package net.ostis.confman.model.datastore.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.ostis.confman.model.convert.EntityConverter;
import net.ostis.confman.model.entity.Participants;
import net.ostis.confman.model.entity.Persons;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Participant;
import net.ostis.confman.services.common.model.Person;

public class IDProvider {

    private Map<Object, Long> idStorage;

    public IDProvider() {

        super();
        this.idStorage = new HashMap<Object, Long>();
    }

    public void init(final FullModel model,
            final EntityConverter entityConverter) {

        formPersons(model.getPersons(), entityConverter.getPersons());
        formParticipants(model.getParticipants(),
                entityConverter.getParticipants());
    }

    private void formPersons(final List<Person> persons,
            final Persons storedPersons) {

        for (final Person person : persons) {
            final long id = findPersonId(person, storedPersons.getPersons());
            this.idStorage.put(person, id);
        }
    }

    private long findPersonId(final Person person,
            final List<net.ostis.confman.model.entity.Person> persons) {

        for (final net.ostis.confman.model.entity.Person storedPerson : persons) {
            if (isSamePersons(person, storedPerson)) {
                return storedPerson.getId();
            }
        }
        return IDGenerator.getInstance().nextId();
    }

    private boolean isSamePersons(final Person person,
            final net.ostis.confman.model.entity.Person storedPerson) {

        final EntityComparer comparer = new EntityComparer();
        return comparer.comparePersons(person, storedPerson);
    }

    private void formParticipants(final List<Participant> participants,
            final Participants storedParticipants) {

        for (final Participant participant : participants) {
            final long id = findParticipantId(participant,
                    storedParticipants.getParticipants());
            this.idStorage.put(participant, id);
        }
    }

    private long findParticipantId(final Participant participant,
            final List<net.ostis.confman.model.entity.Participant> participants) {

        for (final net.ostis.confman.model.entity.Participant storedParticipant : participants) {
            if (isSameParticipants(participant, storedParticipant)) {
                return storedParticipant.getId();
            }
        }
        return IDGenerator.getInstance().nextId();
    }

    private boolean isSameParticipants(final Participant participant,
            final net.ostis.confman.model.entity.Participant storedParticipant) {

        final EntityComparer comparer = new EntityComparer();
        return comparer.compareParticipants(participant, storedParticipant);
    }

    public Long getId(final Object object) {

        return this.idStorage.get(object);
    }
}
