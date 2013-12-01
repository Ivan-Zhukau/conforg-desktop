package net.ostis.confman.ui.common.component.conftree;

import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.TransferData;

public class ConfTreeListenerProvider {

    public ConfTreeListenerProvider() {

        super();
    }

    public ISelectionChangedListener getSelectionChangedListener() {

        return new TreeSelectionChangedListener();
    }

    public IDoubleClickListener getDoubleClickListener() {

        return new TreeDoubleClickListener();
    }

    public IMenuListener getIMenuListener(final TreeViewer treeViewer) {

        return new TreeMenuListener(treeViewer);
    }

    public DragSourceListener getDragSourceListener(final TreeViewer treeViewer) {

        return new TreeDragListener(treeViewer);
    }

    public ViewerDropAdapter getViewerDropAdapter(final TreeViewer treeViewer) {

        return new TreeDropListener(treeViewer);
    }

    private static class TreeSelectionChangedListener implements
            ISelectionChangedListener {

        public TreeSelectionChangedListener() {

            super();

        }

        @Override
        public void selectionChanged(final SelectionChangedEvent event) {

        }
    }

    private static class TreeDoubleClickListener implements
            IDoubleClickListener {

        public TreeDoubleClickListener() {

            super();
        }

        @Override
        public void doubleClick(final DoubleClickEvent event) {

            final TreeViewer viewer = (TreeViewer) event.getViewer();
            final IStructuredSelection thisSelection = (IStructuredSelection) event
                    .getSelection();
            final Object selectedNode = thisSelection.getFirstElement();
            viewer.setExpandedState(selectedNode,
                    !viewer.getExpandedState(selectedNode));
        }
    }

    private static class TreeMenuListener implements IMenuListener {

        private TreeViewer treeViewer;

        public TreeMenuListener(final TreeViewer treeViewer) {

            super();
            this.treeViewer = treeViewer;
        }

        @Override
        public void menuAboutToShow(final IMenuManager manager) {

            final IStructuredSelection selection = (IStructuredSelection) this.treeViewer
                    .getSelection();
            if (!selection.isEmpty()) {
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Conference) {
                    addConferenceActions(manager, (Conference) selectedElement);
                } else if (selectedElement instanceof Section) {
                    addSectionActions(manager, (Section) selectedElement);
                } else if (selectedElement instanceof Report) {
                    addReportActions(manager, (Report) selectedElement);
                }
            }
        }

        private void addConferenceActions(final IMenuManager manager,
                final Conference selectedElement) {

            // TODO kfs: add tree menu item listeners
            final Action a = new Action("Add Section") {
            };
            manager.add(a);
        }

        private void addSectionActions(final IMenuManager manager,
                final Section selectedElement) {

            // TODO kfs: add tree menu item listeners
            final Action a1 = new Action("Add Report") {
            };
            final Action a2 = new Action("Delete") {
            };
            manager.add(a1);
            manager.add(a2);
        }

        private void addReportActions(final IMenuManager manager,
                final Report selectedElement) {

            // TODO kfs: add tree menu item listeners
            final Action a1 = new Action("Delete") {
            };
            final Action a2 = new Action("Move to...") {
            };
            manager.add(a1);
            manager.add(a2);
        }
    }

    private static class TreeDragListener implements DragSourceListener {

        private final TreeViewer treeViewer;

        public TreeDragListener(final TreeViewer treeViewer) {

            this.treeViewer = treeViewer;
        }

        @Override
        public void dragFinished(final DragSourceEvent event) {

            // TODO kfs: provide Finshed Drag event support
        }

        @Override
        public void dragSetData(final DragSourceEvent event) {

            final IStructuredSelection selection = (IStructuredSelection) this.treeViewer
                    .getSelection();
            final Report element = (Report) selection.getFirstElement();

            if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
                event.data = element.getTitle();
            }

        }

        @Override
        public void dragStart(final DragSourceEvent event) {

            // TODO kfs: provide Start Drag event support
        }
    }

    public static class TreeDropListener extends ViewerDropAdapter {

        private enum DropLocation {
            BEFORE_TARGET(1),
            AFTER_TARGET(2),
            ON_TARGET(3),
            INTO_NOTHING(4);

            private int locationNumber;

            private DropLocation(final int locationNumber) {

                this.locationNumber = locationNumber;
            }

            public static DropLocation getDropLocation(final int locationNumber) {

                for (final DropLocation dropLocation : DropLocation.values()) {
                    if (dropLocation.locationNumber == locationNumber) {
                        return dropLocation;
                    }
                }
                return INTO_NOTHING;
            }
        }

        private final TreeViewer treeViewer;

        public TreeDropListener(final TreeViewer treeViewer) {

            super(treeViewer);
            this.treeViewer = treeViewer;
        }

        @Override
        public void drop(final DropTargetEvent event) {

            final int location = this.determineLocation(event);
            switch (DropLocation.getDropLocation(location)) {
                case BEFORE_TARGET:

                    break;
                case AFTER_TARGET:

                    break;
                case ON_TARGET:

                    break;
                case INTO_NOTHING:

                    break;
            }
            super.drop(event);
        }

        @Override
        public boolean performDrop(final Object data) {

            // TODO kfs: move some data in model
            return false;
        }

        @Override
        public boolean validateDrop(final Object target, final int operation,
                final TransferData transferType) {

            return true;
        }
    }
}
