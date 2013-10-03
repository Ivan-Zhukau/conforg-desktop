package net.ostis.confman.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class ConferenceServiceImpl implements ConferenceService {

    @Override
    public List<ConferenceDto> getConferences() {

        final List<ConferenceDto> confs = new ArrayList<>();
        confs.add(createDto("conf1"));
        confs.add(createDto("conf2"));
        confs.add(createDto("conf3"));
        return confs;
    }

    private ConferenceDto createDto(final String title) {

        final ConferenceDto conf = new ConferenceDto();
        conf.setTitle(title);
        conf.setStartDate(new Date());
        conf.setEndDate(new Date());
        return conf;
    }

}
