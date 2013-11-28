package net.ostis.confman.test.xml;

import java.util.Arrays;
import java.util.Date;

import net.ostis.confman.model.datastore.local.SectionWriter;
import net.ostis.confman.model.entity.Section;
import net.ostis.confman.model.entity.Sections;

import org.junit.Test;

public class SectionStorageTest {

    @Test
    public void testSectionSave() {

        final Section section = new Section();
        section.setConferenceId(1L);
        section.setDate(new Date(123124124));
        section.setReports(Arrays.asList(12L, 13L));
        section.setTitle("First");

        final Sections sections = new Sections();
        sections.setSections(Arrays.asList(section, section, section));
        final Runnable command = new SectionWriter(sections);
        command.run();
    }
}
