package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.services.common.model.Section;


public interface SectionService {

    List<Section> getSections();

    void addSection(Section section);

    void fireData();
}
