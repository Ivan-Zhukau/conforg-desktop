package net.ostis.confman.model.datastore.local;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

public abstract class Writer {

    protected static final Logger LOGGER = Logger.getLogger(Writer.class);

    public Writer() {

        super();
    }

    protected synchronized void write(final File location, final Object jaxbElement)
            throws JAXBException {

        final JAXBContext context = JAXBContext.newInstance(jaxbElement
                .getClass());
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(jaxbElement, location);
    }
}
