package net.ostis.confman.ui.common.component.conftree;

import net.ostis.confman.services.ConferenceDto;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class ConfTreeLabelProvider extends LabelProvider {

    @Override
    public String getText(final Object element) {

        if (element instanceof Conference) {
            final Conference conference = (Conference) element;
            return conference.getTitle();
        } else if (element instanceof Section) {
            final Section section = (Section) element;
            return section.getTitle();
        } else if (element instanceof Report) {
            final Report report = (Report) element;
            return report.getTitle();
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Image getImage(final Object element) {

        if (element instanceof ConferenceDto) {
            return null;
        }
        return null;
    }
}
