package net.ostis.confman.test.xml;

import java.util.Arrays;
import java.util.Date;

import net.ostis.confman.model.datastore.local.ConferenceWriter;
import net.ostis.confman.model.entity.Conference;
import net.ostis.confman.model.entity.Conferences;

import org.junit.Test;

public class ConferenceStorageTest {

    @Test
    public void testConferenceSave() {

        final Conference conf = new Conference();
        conf.setTitle("OSTIS-2014");
        conf.setStartDate(new Date(1234566));
        conf.setEndDate(new Date(1234567));
        conf.setParticipants(Arrays.asList(1L, 2L, 3L, 5L));
        conf.setReports(Arrays.asList(11L, 12L, 13L, 14L));
        conf.setSections(Arrays.asList(31L, 32L, 33L, 34L));
        final Conferences conferences = new Conferences();
        conferences.setConferences(Arrays.asList(conf, conf, conf));

        final Runnable command = new ConferenceWriter(conferences);
        command.run();
    }
}
