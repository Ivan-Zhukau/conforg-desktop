package net.ostis.confman.ui.handlers;

import javax.inject.Named;

import net.ostis.confman.ui.preferences.PreferencesDialog;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Shell;

public class ShowPreferencesDialog {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {

        new PreferencesDialog();
    }
}
