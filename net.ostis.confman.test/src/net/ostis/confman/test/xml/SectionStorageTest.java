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
        section.setId(4L);
        section.setConferenceId(3L);
        section.setDate(new Date());
        section.setReports(Arrays.asList(5L));
        section.setTitle("SemanTec");

        final Sections sections = new Sections();
        sections.setSections(Arrays.asList(section));
        final Runnable command = new SectionWriter(sections);
        command.run();
    }
}
