package net.ostis.confman.ui.conference.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import net.ostis.confman.ui.conference.ConferenceTopics;
import net.ostis.confman.ui.conference.parts.PartId;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public class NewConferenceHandler {

    @Inject
    EventBroker eventBroker;

    @Inject
    EPartService partService;

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {

        final MPart part = this.partService.findPart(PartId.CONFERENCE_PART);
        partService.showPart(part, PartState.VISIBLE);
        this.eventBroker.post(ConferenceTopics.CONF_CREATE, null);
    }
}
