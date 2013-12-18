package net.ostis.confman.ui.schedule;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ScheduleService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;
import net.ostis.confman.ui.schedule.tree.ScheduleTreeContentProvider;
import net.ostis.confman.ui.schedule.tree.ScheduleTreeLabelProvider;
import net.ostis.confman.ui.schedule.tree.ScheduleTreeListenerProvider;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class SectionTimeTreePart {

    private static final int  CONF_LEVEL_EXPAND = 2;

    private ScheduleService   scheduleService;

    @Inject
    private ESelectionService selectionService;

    @Inject
    private EPartService      partService;

    @Inject
    private IEventBroker      eventBroker;

    private TreeViewer        treeViewer;

    public SectionTimeTreePart() {

        super();
        this.scheduleService = (ScheduleService) ServiceLocator.getInstance()
                .getService(ScheduleService.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        initTreeViewer(parent);
        addEventSupport();
    }

    private void initTreeViewer(final Composite parent) {

        this.treeViewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL
                | SWT.V_SCROLL);
        this.treeViewer.setContentProvider(new ScheduleTreeContentProvider());
        this.treeViewer.setLabelProvider(new ScheduleTreeLabelProvider());
        this.treeViewer.setAutoExpandLevel(CONF_LEVEL_EXPAND);
        this.treeViewer.setInput(this.scheduleService.getConferences());
    }

    private void addEventSupport() {

        final ScheduleTreeListenerProvider listenerProvider = new ScheduleTreeListenerProvider();
        // TODO kfs: fix drag and drop bugs in TreeViewer
        // addDragAndDropSupport(listenerProvider);
        this.treeViewer.addSelectionChangedListener(listenerProvider
                .getSelectionChangedListener(this.treeViewer,
                        this.selectionService, this.partService));
        this.treeViewer.addDoubleClickListener(listenerProvider
                .getDoubleClickListener());
        initContextMenu(listenerProvider);
    }

    /*
     * private void addDragAndDropSupport( final ScheduleTreeListenerProvider
     * listenerProvider) {
     * 
     * final int operations = DND.DROP_COPY | DND.DROP_MOVE; final
     * LocalSelectionTransfer transfer = LocalSelectionTransfer .getTransfer();
     * final Transfer[] transferTypes = new Transfer[] { transfer };
     * this.treeViewer.addDragSupport(operations, transferTypes,
     * listenerProvider.getDragSourceListener(this.treeViewer));
     * this.treeViewer.addDropSupport(operations, transferTypes,
     * listenerProvider.getViewerDropAdapter(this.treeViewer)); }
     */

    private void initContextMenu(
            final ScheduleTreeListenerProvider listenerProvider) {

        final MenuManager manager = new MenuManager();
        manager.setRemoveAllWhenShown(true);
        manager.addMenuListener(listenerProvider.getIMenuListener(
                this.treeViewer, this.eventBroker, this.partService));
        this.treeViewer.getControl().setMenu(
                manager.createContextMenu(this.treeViewer.getControl()));
    }

    @Focus
    public void setFocus() {

        this.treeViewer.getControl().setFocus();
    }

    @Inject
    @Optional
    private void onConfDataUpdate(
            @UIEventTopic(SectionTimeTreeTopics.CONF_UPDATE) final String s) {

        final IStructuredSelection selection = (IStructuredSelection) this.treeViewer
                .getSelection();
        final Object data = selection.getFirstElement();
        if (data instanceof Conference || data instanceof Section
                || data instanceof Report) {
            this.scheduleService.fireData();
        }
        this.treeViewer.refresh();
    }

    @Inject
    @Optional
    private void onTreeDataUpdate(
            @UIEventTopic(SectionTimeTreeTopics.CONF_TREE_UPDATE) final String s) {

        this.treeViewer.refresh();
    }
}
