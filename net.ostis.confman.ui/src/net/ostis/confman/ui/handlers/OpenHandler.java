/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package net.ostis.confman.ui.handlers;

import javax.inject.Named;

import net.ostis.confman.services.RegistrationService;
import net.ostis.confman.services.ServiceLocator;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class OpenHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) final Shell shell) {

        final FileDialog dialog = new FileDialog(shell);
        final String filePath = dialog.open();
        final RegistrationService service = (RegistrationService) ServiceLocator
                .getInstance().getService(RegistrationService.class);
        service.parseForm(filePath);
    }
}
