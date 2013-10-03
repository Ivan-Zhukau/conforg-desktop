package net.ostis.confman.ui.conference;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ConferenceDto;
import net.ostis.confman.services.ConferenceService;
import net.ostis.confman.services.ServiceLocator;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class ConferencesView {

    private ConferenceService            confService;

    @Inject
    private ESelectionService            selectionService;

    private final List<ConferenceDto>    conferences;

    private org.eclipse.swt.widgets.List confUiList;

    public ConferencesView() {

        super();
        this.confService = (ConferenceService) ServiceLocator.getInstance()
                .getService(ConferenceService.class);
        this.conferences = new ArrayList<>();
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        parent.setLayout(new GridLayout());
        this.conferences.addAll(this.confService.getConferences());
        this.confUiList = new org.eclipse.swt.widgets.List(parent, SWT.BORDER
                | SWT.MULTI | SWT.V_SCROLL);
        fillConferenceList();
        this.confUiList.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                final org.eclipse.swt.widgets.List source = (org.eclipse.swt.widgets.List) e
                        .getSource();
                ConferenceDto confFound = null;
                for (final ConferenceDto conf : ConferencesView.this.conferences) {
                    if (source.getSelection()[0].equals(conf.getTitle())) {
                        confFound = conf;
                        break;
                    }
                }
                if (confFound != null) {
                    ConferencesView.this.selectionService
                            .setSelection(confFound);
                }
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

            }
        });
    }

    private void fillConferenceList() {

        for (final ConferenceDto conf : this.conferences) {
            this.confUiList.add(conf.getTitle());
        }
    }

    @Inject
    @Optional
    private void onConfDataUpdate(
            @UIEventTopic(ConferenceTopics.CONF_SAVE) final String s) {

        this.confUiList.removeAll();
        fillConferenceList();
    }

}
