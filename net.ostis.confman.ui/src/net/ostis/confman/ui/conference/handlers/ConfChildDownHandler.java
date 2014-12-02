package net.ostis.confman.ui.conference.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import net.ostis.confman.ui.conference.ConferenceTopics;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public class ConfChildDownHandler {

    @Inject
    EventBroker eventBroker;

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {

        this.eventBroker.post(ConferenceTopics.CONF_CHILD_DOWN, null);
    }
}