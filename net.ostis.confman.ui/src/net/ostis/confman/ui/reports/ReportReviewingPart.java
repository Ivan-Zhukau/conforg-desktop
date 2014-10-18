package net.ostis.confman.ui.reports;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.ServiceLocator;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.EditableComponent;
import net.ostis.confman.ui.common.component.StringDataConverter;
import net.ostis.confman.ui.common.component.TextField;
import net.ostis.confman.ui.common.component.ValueBinder;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;
import net.ostis.confman.ui.conference.ConferenceTopics;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class ReportReviewingPart {

    private static final int LAYOUT_COL_COUNT = 1;

    private Report           currentReport;

    private enum ReportCaptions implements Localizable {
        TITLE("reportTitle"),
        ACCEPT_REPORT("acceptReport"),
        CANCEL_REPORT("cancelReport");

        private String rk;

        private ReportCaptions(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private final Map<ReportCaptions, EditableComponent<?>> editFields;

    @Inject
    private ESelectionService                               selectionService;

    private ConferenceService                               conferenceService;

    @Inject
    private IEventBroker                                    eventBroker;

    public ReportReviewingPart() {

        super();
        this.editFields = new EnumMap<>(ReportCaptions.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        this.conferenceService = (ConferenceService) ServiceLocator
                .getInstance().getService(ConferenceService.class);

        this.selectionService.addSelectionListener(new ISelectionListener() {

            @Override
            public void selectionChanged(final MPart part,
                    final Object selection) {

                if (selection instanceof Report) {
                    final Report report = (Report) selection;
                    currentReport = report;
                    onReportEvent(report, parent);
                }
            }
        });
        buildLayout(parent);
    }

    protected void onReportEvent(final Report report, final Composite parent) {

        applyValueBindings(report);
        for (final ReportCaptions field : this.editFields.keySet()) {
            this.editFields.get(field).activate();
        }
    }

    private void applyValueBindings(final Report report) {

        this.editFields.get(ReportCaptions.TITLE).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        report.setTitle((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return report.getTitle();
                    }
                });
    }

    private void buildLayout(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));

        this.editFields.put(ReportCaptions.TITLE,
                new TextField(parent, util.translate(ReportCaptions.TITLE))
                        .setDataConverter(new StringDataConverter()));

        final Button acceptButton = new Button(parent, SWT.PUSH);
        acceptButton.setText(util.translate(ReportCaptions.ACCEPT_REPORT));
        acceptButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                setAccepting();
                save();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                setAccepting();
                save();
            }
        });

        final Button cancelButton = new Button(parent, SWT.PUSH);
        cancelButton.setText(util.translate(ReportCaptions.CANCEL_REPORT));
        cancelButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                setCanceling();
                save();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                setCanceling();
                save();
            }
        });
    }

    private void setAccepting() {

        if (currentReport != null) {
            currentReport.setReportAccepted(true);
            currentReport.setReportCanceled(false);
        }

    }

    private void setCanceling() {

        if (currentReport != null) {
            currentReport.setReportAccepted(false);
            currentReport.setReportCanceled(true);
            if(currentReport.getSection() != null && currentReport.getSection().getConference() != null) {
                conferenceService.deleteReport(currentReport);
            }
        }
    }

    private void save() {

        if (currentReport != null) {
            for (final ReportCaptions field : this.editFields.keySet()) {
                this.editFields.get(field).apply();
            }
            this.eventBroker.post(ConferenceTopics.CONF_TREE_UPDATE, null);
            this.eventBroker.post(ConferenceTopics.CONF_UPDATE, null);
            this.eventBroker.post(ConferenceTopics.TABLE_UPDATE, null);
        }
    }
}