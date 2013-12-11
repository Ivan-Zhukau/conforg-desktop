package net.ostis.confman.ui.participant;

import javax.inject.Inject;
import javax.inject.Named;

import net.ostis.confman.ui.conference.ConferenceTopics;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.eclipse.swt.widgets.Shell;

public class NewParticipantActionListener {

    @SuppressWarnings("restriction")
    @Inject
    EventBroker eventBroker;

    @SuppressWarnings("restriction")
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {

        this.eventBroker.post(ConferenceTopics.ADD_NEW_PARTICIPANT, null);
    }
}
