package net.ostis.confman.model.datastore.local;

import java.io.FileNotFoundException;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBException;

import net.ostis.confman.model.entity.SectionSettings;

public class SectionSettingsReader extends Reader implements
        Callable<SectionSettings> {

    public SectionSettingsReader() {

        super();
    }

    @Override
    public SectionSettings call() throws Exception {

        try {
            return (SectionSettings) read(
                    LocalStorageConstants.SECTION_CONFIG_FILE_LOCATION,
                    SectionSettings.class);
        } catch (final FileNotFoundException e) {
            LOGGER.error(e);
        } catch (final JAXBException e) {
            LOGGER.error(e);
        }
        throw new DatastoreException();
    }
}
