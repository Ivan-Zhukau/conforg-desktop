package net.ostis.confman.model.datastore.local.convert;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.datastore.util.IDProvider;
import net.ostis.confman.model.entity.Address;
import net.ostis.confman.model.entity.Conference;
import net.ostis.confman.model.entity.Conferences;
import net.ostis.confman.services.common.model.FullModel;

class ConferenceConverter {

    public static Conferences convert(final FullModel model,
            final IDProvider idProvider) {

        final List<Conference> conferencesToStore = convertConferences(
                model.getConferences(), idProvider);
        final Conferences conferenceStorage = new Conferences();
        conferenceStorage.setConferences(conferencesToStore);
        return conferenceStorage;
    }

    private static List<Conference> convertConferences(
            final List<net.ostis.confman.services.common.model.Conference> conferences,
            final IDProvider idProvider) {

        final List<Conference> conferencesToStore = new ArrayList<>();
        Conference conferenceToStore;
        for (final net.ostis.confman.services.common.model.Conference conference : conferences) {
            conferenceToStore = convertConference(conference, idProvider);
            conferencesToStore.add(conferenceToStore);
        }
        return conferencesToStore;
    }

    private static Conference convertConference(
            final net.ostis.confman.services.common.model.Conference conference,
            final IDProvider idProvider) {

        final Conference conferenceToStore = new Conference();
        conferenceToStore.setTitle(conference.getTitle());
        conferenceToStore.setStartDate(conference.getStartDate());
        conferenceToStore.setEndDate(conference.getEndDate());
        conferenceToStore.setResidence(convertResidence(conference
                .getConferenceVenue()));
        conferenceToStore.setId(idProvider.getId(conference));
        conferenceToStore.setParticipants(getIds(conference.getParticipants(),
                idProvider));
        conferenceToStore
                .setReports(getIds(conference.getReports(), idProvider));
        conferenceToStore.setSections(getIds(conference.getSections(),
                idProvider));
        return conferenceToStore;
    }

    private static List<Long> getIds(final List<?> objects,
            final IDProvider idProvider) {

        final List<Long> ids = new ArrayList<>();
        for (final Object object : objects) {
            final Long id = idProvider.getId(object);
            ids.add(id);
        }
        return ids;
    }

    private static Address convertResidence(
            final net.ostis.confman.services.common.model.Address conferenceVenue) {

        final Address address = new Address();
        address.setCity(conferenceVenue.getCity());
        address.setCountry(conferenceVenue.getCountry());
        address.setHouseNumber(conferenceVenue.getHouseNumber());
        address.setStreet(conferenceVenue.getStreet());
        return address;
    }
}
