package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.model.datastore.XmlStorageProvider;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Section;

class TimeSectionServiceImpl implements TimeSectionService {

    List<Section> sections;

    FullModel     model;

    public TimeSectionServiceImpl() {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
        this.sections = this.model.getSections();
    }

    @Override
    public void fireData() {

        final XmlStorageProvider storageProvider = XmlStorageProvider.getInstance();
        storageProvider.persist(this.model);
    }

    @Override
    public List<Section> getSections() {

        return this.sections;
    }
}
