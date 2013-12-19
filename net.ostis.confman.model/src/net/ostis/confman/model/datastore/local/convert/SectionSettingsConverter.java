package net.ostis.confman.model.datastore.local.convert;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.datastore.util.IDProvider;
import net.ostis.confman.model.entity.SectionBreaks;
import net.ostis.confman.model.entity.SectionSettings;
import net.ostis.confman.services.common.model.FullModel;

public class SectionSettingsConverter {

    public static SectionSettings convert(final FullModel model,
            final IDProvider idProvider) {

        final SectionSettings sectionSettings = new SectionSettings();
        final List<SectionBreaks> breaks = convertBreaks(
                model.getSectionSettings(), idProvider);
        sectionSettings.setSectionBreaks(breaks);
        return sectionSettings;
    }

    private static List<SectionBreaks> convertBreaks(
            final List<net.ostis.confman.services.common.model.SectionSettings> sectionSettingsList,
            final IDProvider idProvider) {

        final List<SectionBreaks> breaksList = new ArrayList<>();
        for (final net.ostis.confman.services.common.model.SectionSettings settings : sectionSettingsList) {
            final SectionBreaks sectionBreaks = convertSettings(settings,
                    idProvider);
            breaksList.add(sectionBreaks);
        }
        return breaksList;
    }

    private static SectionBreaks convertSettings(
            final net.ostis.confman.services.common.model.SectionSettings settings,
            final IDProvider idProvider) {

        final SectionBreaks breaks = new SectionBreaks();
        breaks.setSectionId(idProvider.getId(settings.getSection()));
        breaks.setBreakTime(settings.getBreakTime());
        breaks.setChairmanTime(settings.getChairmanTime());
        breaks.setPlenaryReportTime(settings.getPlenaryReportTime());
        breaks.setReportTime(settings.getReportTime());
        breaks.setCoffeeBreaksNum(settings.getCoffeeBreakNumber());
        breaks.setCoffeeBreaksTime(settings.getCoffeeBreakTime());
        return breaks;
    }

}
