package net.ostis.confman.ui.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class SwitchPerspectiveHandler {

    private static final String PERSPECTIVE_CMD_PARAM = "net.ostis.confman.ui.parameter.perspectiveId";

    @Inject
    private EPartService        partService;

    @Inject
    private EModelService       modelService;

    public SwitchPerspectiveHandler() {

        super();
    }

    @Execute
    public void changePerspective(final MApplication app,
            @Named(PERSPECTIVE_CMD_PARAM) final String newPerspId) {

        final MPerspective newPerspective = (MPerspective) this.modelService
                .find(newPerspId, app);
        this.partService.switchPerspective(newPerspective);
    }
}
