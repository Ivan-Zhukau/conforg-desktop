package net.ostis.confman.ui.participant;

import javax.inject.Inject;
import javax.inject.Named;

import net.ostis.confman.services.common.model.Participant;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.widgets.Shell;


public class NewParticipantActionListener{
    
    @Inject
    ESelectionService selectionService;
    
    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {

        selectionService.setSelection(new Participant());
    }
}
