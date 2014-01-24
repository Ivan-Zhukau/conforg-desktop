package net.ostis.confman.ui.preferences;

import net.ostis.confman.ui.common.Localizable;
import net.ostis.confman.ui.common.component.util.LocalizationUtil;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class EmailPreferencesPage extends FieldEditorPreferencePage {

    private enum Fields implements Localizable {

        PAGE_TITLE("emailPreferences", "none"),
        USER_NAME("emailUserName",
                "net.ostis.confman.preferences.email.username"),
        PASSWORD("emailPassword",
                "net.ostis.confman.preferences.email.password");

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

    public EmailPreferencesPage() {

        super(LocalizationUtil.getInstance().translate(Fields.PAGE_TITLE), GRID);
    }

    @Override
    protected void createFieldEditors() {

        final LocalizationUtil localizationUtil = LocalizationUtil
                .getInstance();
        createUsernameEditor(localizationUtil);
        createPasswordEditor(localizationUtil);
    }

    private void createUsernameEditor(final LocalizationUtil localizationUtil) {

        final StringFieldEditor userNameEditor = new StringFieldEditor(
                Fields.USER_NAME.getId(),
                localizationUtil.translate(Fields.USER_NAME.getResourceKey()),
                getFieldEditorParent());
        addField(userNameEditor);
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

}
