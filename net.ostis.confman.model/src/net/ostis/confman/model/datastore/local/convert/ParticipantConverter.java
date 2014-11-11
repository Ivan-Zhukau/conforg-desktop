package net.ostis.confman.model.datastore.local.convert;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.datastore.util.IDProvider;
import net.ostis.confman.model.entity.Address;
import net.ostis.confman.model.entity.Participant;
import net.ostis.confman.model.entity.ParticipantArrival;
import net.ostis.confman.model.entity.ParticipantRole;
import net.ostis.confman.model.entity.Participants;
import net.ostis.confman.model.entity.ParticipationInConference;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Report;

public class ParticipantConverter {

    public static Participants convert(final FullModel model,
            final IDProvider idProvider) {

        final List<Participant> participantsToStore = convertParticipants(
                model.getParticipants(), idProvider);
        final Participants storage = new Participants();
        storage.setParticipants(participantsToStore);
        return storage;
    }

    private static List<Participant> convertParticipants(
            final List<net.ostis.confman.services.common.model.Participant> participants,
            final IDProvider idProvider) {

        final List<Participant> participantsToStore = new ArrayList<Participant>();
        Participant participantToStore;
        for (final net.ostis.confman.services.common.model.Participant participant : participants) {
            participantToStore = convertFields(participant, idProvider);
            participantsToStore.add(participantToStore);
        }
        return participantsToStore;
    }

    private static Participant convertFields(
            final net.ostis.confman.services.common.model.Participant participant,
            final IDProvider idProvider) {

        final Participant participantToStore = new Participant();
        participantToStore.setArrival(convertArrival(participant.getArrival()));
        participantToStore.setConferenceId(idProvider.getId(participant
                .getConference()));
        participantToStore.setId(idProvider.getId(participant));
        participantToStore
                .setPersonId(idProvider.getId(participant.getPerson()));
        participantToStore.setReportId(getReportId(participant.getReports(),
                idProvider));
        participantToStore.setRole(convertRole(participant.getRole()));
        participantToStore
                .setParticipationInConference(convertPartisipation(participant
                        .getParticipationInConference()));
        return participantToStore;
    }

    private static ParticipantRole convertRole(
            final net.ostis.confman.services.common.model.ParticipantRole role) {

        final ParticipantRole participantRole = new ParticipantRole();
        participantRole.setExhibitionStand(role.getExhibitionStand());
        participantRole.setParticipationForm(role.getParticipationForm());
        participantRole.setParticipationCategory(role
                .getParticipationCategory());
        participantRole.setProgramCommitteeMember(role
                .getProgramCommitteeMember());
        return participantRole;
    }

    private static ParticipationInConference convertPartisipation(
            final net.ostis.confman.services.common.model.ParticipationInConference p) {

        final ParticipationInConference partisipation = new ParticipationInConference();
        partisipation.setExhibitionPresentationOfeports(p
                .getExhibitionPresentationOfeports());
        partisipation.setTourOfTheCityOfMinsk(p.getTourOfTheCityOfMinsk());
        partisipation.setCulturalProgram(p.getCulturalProgram());
        partisipation.setEveningMeetingPC(p.getEveningMeetingPC());
        return partisipation;
    }

    private static ParticipantArrival convertArrival(
            final net.ostis.confman.services.common.model.ParticipantArrival arrival) {

        final ParticipantArrival participantArrival = new ParticipantArrival();
        participantArrival.setHousing(arrival.getHousing());
        participantArrival.setMeeting(arrival.getMeeting());
        participantArrival.setIsHostelReservation(arrival
                .getHostelReservation());
        participantArrival.setResidencePlace(convertResidencePlace(arrival
                .getResidencePlace()));
        return participantArrival;
    }

    private static Address convertResidencePlace(
            final net.ostis.confman.services.common.model.Address residencePlace) {

        final Address address = new Address();
        address.setCity(residencePlace.getCity());
        address.setCountry(residencePlace.getCountry());
        address.setHouseNumber(residencePlace.getHouseNumber());
        address.setStreet(residencePlace.getStreet());
        return address;
    }

    private static List<Long> getReportId(final List<Report> reports,
            final IDProvider idProvider) {

        final List<Long> ids = new ArrayList<>();
        for (final Report report : reports) {
            final long id = idProvider.getId(report);
            ids.add(id);
        }
        return ids;
    }
}
