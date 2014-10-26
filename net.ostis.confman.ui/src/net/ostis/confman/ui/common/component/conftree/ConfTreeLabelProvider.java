package net.ostis.confman.ui.common.component.conftree;

import net.ostis.confman.services.ConferenceDto;
import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ConfTreeLabelProvider extends LabelProvider {

    private static final String SECTION_PREFIX = "sectionPrefix";

    private ConferenceService confService;

    public ConfTreeLabelProvider() {
        super();
        this.confService = (ConferenceService) ServiceLocator.getInstance()
                .getService(ConferenceService.class);
    }

    @Override
    public String getText(final Object element) {

        if (element instanceof Conference) {
            final Conference conference = (Conference) element;
            return conference.getTitle();
        } else if (element instanceof Section) {
            final Section section = (Section) element;
            return generateSectionLabel(section);
        } else if (element instanceof Report) {
            final Report report = (Report) element;
            return report.getTitle();
        }
        throw new IllegalArgumentException();
    }

    private String generateSectionLabel(Section section) {
        StringBuilder temp = new StringBuilder();
        int adjustedIndex = confService.getSectionOrder(section) + 1;
        String sectionPrefix = LocalizationUtil.getInstance().translate(SECTION_PREFIX);
        temp.append(sectionPrefix)
            .append(adjustedIndex)
            .append(". ")
            .append(section.getTitle());
        return temp.toString();
    }

    @Override
    public Image getImage(final Object element) {

        if (element instanceof ConferenceDto) {
            return null;
        }
        return null;
    }
}
