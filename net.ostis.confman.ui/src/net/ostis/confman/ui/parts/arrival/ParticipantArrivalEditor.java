package net.ostis.confman.ui.parts.arrival;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.widgets.Composite;


public class ParticipantArrivalEditor {

    @Inject
    private ESelectionService selectionService;

    @Inject
    private EPartService      partService;

    @Inject
    private IEventBroker      eventBroker;

    public ParticipantArrivalEditor() {
        super();
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        //initTreeViewer(parent);
    }

}
