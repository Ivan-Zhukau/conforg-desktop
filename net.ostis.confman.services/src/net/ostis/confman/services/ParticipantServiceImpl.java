package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.model.datastore.StorageProvider;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Participant;

public class ParticipantServiceImpl implements ParticipantService {

    List<Participant> participants;

    FullModel         model;

    public ParticipantServiceImpl() {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
        this.participants = this.model.getParticipants();
    }

    @Override
    public List<Participant> getParticipants() {

        return this.participants;
    }

    @Override
    public void addParticipant(final Participant participant) {

        this.participants.add(participant);
    }

    @Override
    public void fireData() {

        final StorageProvider storageProvider = StorageProvider.getInstance();
        storageProvider.persist(this.model);
    }

}
