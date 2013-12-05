package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.model.convert.ConverterFromStorageProvider;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.FullModel;

class ConferenceServiceImpl implements ConferenceService {

    List<Conference> conferences;

    public ConferenceServiceImpl() {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        final FullModel model = converter.convertData();
        this.conferences = model.getConferences();
    }

    @Override
    public List<Conference> getConferences() {

        return this.conferences;
    }
}
