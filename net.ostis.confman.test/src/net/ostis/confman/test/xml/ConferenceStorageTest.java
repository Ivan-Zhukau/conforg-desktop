package net.ostis.confman.test.xml;

import java.util.Arrays;
import java.util.Date;

import net.ostis.confman.model.datastore.local.ConferenceWriter;
import net.ostis.confman.model.entity.xml.Conference;
import net.ostis.confman.model.entity.xml.Conferences;

import org.junit.Test;

public class ConferenceStorageTest {

    @Test
    public void testConferenceSave() {

        final Conference conf = new Conference();
        conf.setId(3L);
        conf.setTitle("OSTIS-2014");
        conf.setStartDate(new Date());
        conf.setEndDate(new Date());
        conf.setParticipants(Arrays.asList(2L));
        conf.setReports(Arrays.asList(5L));
        conf.setSections(Arrays.asList(4L));
        final Conferences conferences = new Conferences();
        conferences.setConferences(Arrays.asList(conf));

        final Runnable command = new ConferenceWriter(conferences);
        command.run();
    }
}
