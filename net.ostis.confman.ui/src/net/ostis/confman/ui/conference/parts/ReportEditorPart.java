package net.ostis.confman.ui.conference.parts;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.ostis.confman.services.ConferenceDto;
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

public class ReportEditorPart {

    private static final int LAYOUT_COL_COUNT = 1;

    private enum ConferenceFields implements Localizable {
        TITLE("reportTitle"),
        SECTION("section"),
        MAIN_AUTHOR("mainAuthor");

        private String rk;

        private ConferenceFields(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }
    }

    private enum Buttons implements Localizable {
        SAVE("save");

        private String rk;

        private Buttons(final String rk) {

            this.rk = rk;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }

    }

    private final Map<ConferenceFields, EditableComponent<TextField>> editFields;

    @Inject
    private ESelectionService                                         selectionService;

    @Inject
    private IEventBroker                                              eventBroker;

    public ReportEditorPart() {

        super();
        this.editFields = new EnumMap<>(ConferenceFields.class);
    }

    @PostConstruct
    public void createComposite(final Composite parent) {

        this.selectionService.addSelectionListener(new ISelectionListener() {

            @Override
            public void selectionChanged(final MPart part,
                    final Object selection) {

                if (!(selection instanceof ConferenceDto)) {
                    return;
                }
                final ConferenceDto conf = (ConferenceDto) selection;
                onNewSelection(conf);
            }
        });
        buildLayout(parent);
    }

    private void onNewSelection(final ConferenceDto conf) {

        applyValueBindings(conf);
        for (final ConferenceFields field : this.editFields.keySet()) {
            this.editFields.get(field).activate();
        }
    }

    private void applyValueBindings(final ConferenceDto conf) {

        this.editFields.get(ConferenceFields.TITLE).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        conf.setTitle((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return conf.getTitle();
                    }
                });
        this.editFields.get(ConferenceFields.SECTION).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        conf.setTitle((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return conf.getTitle();
                    }
                });
        this.editFields.get(ConferenceFields.MAIN_AUTHOR).setValueBinder(
                new ValueBinder() {

                    @Override
                    public void setValue(final Object value) {

                        conf.setTitle((String) value);
                    }

                    @Override
                    public Object getValue() {

                        return conf.getTitle();
                    }
                });
    }

    private void buildLayout(final Composite parent) {

        final LocalizationUtil util = LocalizationUtil.getInstance();
        parent.setLayout(new GridLayout(LAYOUT_COL_COUNT, true));
        this.editFields.put(ConferenceFields.TITLE,
                new TextField(parent, util.translate(ConferenceFields.TITLE))
                        .setDataConverter(new StringDataConverter()));
        this.editFields.put(ConferenceFields.SECTION, new TextField(parent,
                util.translate(ConferenceFields.SECTION))
                .setDataConverter(new StringDataConverter()));
        this.editFields.put(ConferenceFields.MAIN_AUTHOR, new TextField(parent,
                util.translate(ConferenceFields.MAIN_AUTHOR))
                .setDataConverter(new StringDataConverter()));
        final Button button = new Button(parent, SWT.PUSH);
        button.setText(util.translate(Buttons.SAVE));
        button.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                onSave();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

                onSave();
            }
        });
    }

    private void onSave() {

        for (final ConferenceFields field : this.editFields.keySet()) {
            this.editFields.get(field).apply();
        }
        this.eventBroker.post(ConferenceTopics.CONF_UPDATE, null);
    }
}
