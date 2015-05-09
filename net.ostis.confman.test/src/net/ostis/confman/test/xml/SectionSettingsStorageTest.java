package net.ostis.confman.test.xml;

import java.util.Arrays;

import net.ostis.confman.model.datastore.local.SectionSettingsWriter;
import net.ostis.confman.model.entity.xml.SectionBreaks;
import net.ostis.confman.model.entity.xml.SectionSettings;

import org.junit.Test;

public class SectionSettingsStorageTest {

    @Test
    public void test() throws Exception {

        final SectionBreaks sectionBreaks = new SectionBreaks();
        sectionBreaks.setSectionId(0);

        final SectionSettings settings = new SectionSettings();
        settings.setSectionBreaks(Arrays.asList(sectionBreaks));
        
        final Runnable command = new SectionSettingsWriter(settings);
        command.run();
    }
}
