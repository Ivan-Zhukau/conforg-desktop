package net.ostis.confman.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;

class ConferenceServiceImpl implements ConferenceService {

    @Override
    public List<Conference> getConferences() {

        final List<Conference> confs = new ArrayList<>();
        confs.add(createConference("conf1"));
        confs.add(createConference("conf2"));
        return confs;
    }

    private Conference createConference(final String title) {

        final Conference conf = new Conference();
        conf.setTitle(title);
        conf.setStartDate(new Date());
        conf.setEndDate(new Date());
        Section s1 = new Section();
        s1.setConference(conf);
        s1.setDate(new Date());
        s1.setTitle("First");
        Report r1 = new Report();
        r1.setTitle("rep1");
        Report r2 = new Report();
        r2.setTitle("rep2");
        Report r3 = new Report();
        r3.setTitle("rep3");
        s1.setReports(Arrays.asList(r1, r2, r3));
        Section s2 = new Section();
        s2.setConference(conf);
        s2.setDate(new Date());
        s2.setTitle("Second");
        s2.setReports(Arrays.asList(r2, r3));
        conf.setSections(Arrays.asList(s1, s2));
        return conf;
    }

}
