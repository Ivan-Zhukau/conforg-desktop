package net.ostis.confman.ui.preferences;

import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class ProxyPreferencesPage extends FieldEditorPreferencePage {

    private enum Fields implements Localizable {
        PAGE_TITLE("proxyPageTitle", "none"),
        IS_ENABLED("proxyIsEnabled",
                "net.ostis.confman.preferences.proxy.isenabled"),
        USER_NAME("proxyUserName",
                "net.ostis.confman.preferences.proxy.username"),
        PASSWORD("proxyPassword",
                "net.ostis.confman.preferences.proxy.password"),
        HOST("proxyHost", "net.ostis.confman.preferences.proxy.host"),
        PORT("proxyPort", "net.ostis.confman.preferences.proxy.port");

        private String rk;

        private String id;

        private Fields(final String rk, final String id) {

            this.rk = rk;
            this.id = id;
        }

        @Override
        public String getResourceKey() {

            return this.rk;
        }

        public String getId() {

            return this.id;
        }

    }

    public ProxyPreferencesPage() {

        super(LocalizationUtil.getInstance().translate(Fields.PAGE_TITLE), GRID);
    }

    @Override
    protected void createFieldEditors() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        createEnabledEditor(localizationUtil);
        createHostEditor(localizationUtil);
        createPortEditor(localizationUtil);
        createUsernameEditor(localizationUtil);
        createPasswordEditor(localizationUtil);
    }

    private void createPasswordEditor(final LocalizationUtil localizationUtil) {

        final StringFieldEditor passwordEditor = new StringFieldEditor(
                Fields.PASSWORD.getId(),
                localizationUtil.translate(Fields.PASSWORD.getResourceKey()),
                getFieldEditorParent()) {

            @Override
            protected void doFillIntoGrid(final Composite parent,
                    final int numColumns) {

                super.doFillIntoGrid(parent, numColumns);
                getTextControl().setEchoChar('*');
            }
        };
        addField(passwordEditor);
    }

    private void createUsernameEditor(final LocalizationUtil localizationUtil) {

        final StringFieldEditor userNameEditor = new StringFieldEditor(
                Fields.USER_NAME.getId(),
                localizationUtil.translate(Fields.USER_NAME.getResourceKey()),
                getFieldEditorParent());
        addField(userNameEditor);
    }

    private void createPortEditor(final LocalizationUtil localizationUtil) {

        final StringFieldEditor portEditor = new StringFieldEditor(
                Fields.PORT.getId(), localizationUtil.translate(Fields.PORT
                        .getResourceKey()), getFieldEditorParent());
        addField(portEditor);
    }

    private void createHostEditor(final LocalizationUtil localizationUtil) {

        final StringFieldEditor hostEditor = new StringFieldEditor(
                Fields.HOST.getId(), localizationUtil.translate(Fields.HOST
                        .getResourceKey()), getFieldEditorParent());
        addField(hostEditor);
    }

    private void createEnabledEditor(final LocalizationUtil localizationUtil) {

        final BooleanFieldEditor isEnabledEditor = new BooleanFieldEditor(
                Fields.IS_ENABLED.getId(),
                localizationUtil.translate(Fields.IS_ENABLED.getResourceKey()),
                getFieldEditorParent());
        addField(isEnabledEditor);
    }
}
