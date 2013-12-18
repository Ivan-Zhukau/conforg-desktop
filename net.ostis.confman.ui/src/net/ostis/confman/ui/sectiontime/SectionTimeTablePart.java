package net.ostis.confman.ui.sectiontime;

import javax.annotation.PostConstruct;
import javax.inject.Inject;


import net.ostis.confman.services.SectionService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Section;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.DateDataConverter;
import net.ostis.confman.ui.common.component.table.DynamicalTable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.conference.ConferenceTopics;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class SectionTimeTablePart {

    private enum TableColumns implements Localizable {
        NAME("section"),
        DATE("sectionTimeStart");

        private String rk;

        private TableColumns(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    @Inject
    private ESelectionService  selectionService;

    private SectionService sectionService;

    private DynamicalTable     table;

    public SectionTimeTablePart() {

        super();
        this.sectionService = (SectionService) ServiceLocator
                .getInstance().getService(SectionService.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        parent.setLayout(new FillLayout());
        this.table = new DynamicalTable(parent, Boolean.TRUE, SWT.SINGLE);
        createColumns();
        addTableEventSupport();
    }

    private void createColumns() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        final int COLUMN_WIDTH = 150;
        this.table.createColumn(localizationUtil.translate(TableColumns.NAME),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Section section = (Section) element;
                        return section.getTitle();
                    }
                });

        this.table.createColumn(localizationUtil.translate(TableColumns.DATE),
                COLUMN_WIDTH, new ColumnLabelProvider() {

                    @Override
                    public String getText(final Object element) {

                        final Section section = (Section) element;
                        return new DateDataConverter().convert(section.getDate());
                    }
                });
    }

    private void addTableEventSupport() {

        this.table.setContentProvider(ArrayContentProvider.getInstance());
        this.table.setInput(this.sectionService.getSections());
        this.table.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {

               /* final IStructuredSelection selection = (IStructuredSelection) TableViewPart.this.table
                        .getViewer().getSelection();
                final Object selectedElement = selection.getFirstElement();
                if (selectedElement instanceof Participant) {
                    TableViewPart.this.selectionService
                            .setSelection(selectedElement);
                }*/
            }
        });
    }

    @Inject
    @Optional
    private void onConfDataUpdate(
            @UIEventTopic(ConferenceTopics.TABLE_UPDATE) final String s) {

        this.table.refresh();
    }
}
