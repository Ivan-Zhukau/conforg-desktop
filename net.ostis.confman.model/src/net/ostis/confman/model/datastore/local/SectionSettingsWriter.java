package net.ostis.confman.model.datastore.local;

import java.io.File;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.xml.SectionSettings;

public class SectionSettingsWriter extends Writer implements Runnable {

    private SectionSettings data;

    public SectionSettingsWriter(final SectionSettings data) {

        super();
        this.data = data;
    }

    @Override
    public void run() {

        try {
            write(new File(LocalStorageConstants.SECTION_CONFIG_FILE_LOCATION),
                    this.data);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
    }

}
