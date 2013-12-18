package net.ostis.confman.services;

import java.util.List;

import net.ostis.confman.services.common.model.Section;

public interface TimeSectionService {

    List<Section> getSections();

    void fireData();
}
