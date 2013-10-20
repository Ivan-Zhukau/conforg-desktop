package net.ostis.confman.model.datastore.local;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

public abstract class Reader {

    protected static final Logger LOGGER = Logger.getLogger(Reader.class);

    public Reader() {

        super();
    }

    protected Object read(final String location, final Class<?> c)
            throws JAXBException, FileNotFoundException {

        final JAXBContext context = JAXBContext.newInstance(c);
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(new FileReader(location));
    }
}
