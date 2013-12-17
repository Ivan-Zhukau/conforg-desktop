package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.model.datastore.StorageProvider;
import net.ostis.confman.model.datastore.local.convert.ConverterFromStorageProvider;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Section;


public class SectionServiceImpl implements SectionService{

    List<Section> sections;

    FullModel         model;
    
    public SectionServiceImpl() {

        final ConverterFromStorageProvider converter = new ConverterFromStorageProvider();
        this.model = converter.convertData();
        this.sections = this.model.getSections();
    }

    @Override
    public List<Section> getSections() {
        
        return this.sections;
    }

    @Override
    public void addSection(Section section) {

        sections.add(section);        
    }

    @Override
    public void fireData() {

        final StorageProvider storageProvider = StorageProvider.getInstance();
        storageProvider.persist(this.model);        
    }

}
