package net.ostis.confman.model.datastore.local;

import java.io.File;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.xml.Sections;

public class SectionWriter extends Writer implements Runnable {

    private Sections sections;

    public SectionWriter(final Sections sections) {

        super();
        this.sections = sections;
    }

    @Override
    public void run() {

        try {
            write(new File(LocalStorageConstants.SECTIONS_FILE_LOCATION),
                    this.sections);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
    }
}
