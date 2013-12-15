package net.ostis.confman.model.mail;

import java.util.ResourceBundle;

import net.ostis.confman.model.mail.entity.MailDto;

public class MailSettingsProvider {

    private static final String SETTINGS_BUNDLE_NAME = "resourses/Settings";

    private static final String EMAIL_PROPERTY       = "smtpEmail";

    private static final String PASSWORD_PROPERTY    = "smtpPassword";

    public MailSettingsProvider() {

        super();
    }

    public MailDto getMailSettings() {

        final MailDto dto = new MailDto();
        loadSettings(dto);
        return dto;
    }

    private void loadSettings(final MailDto dto) {

        // TODO kfs: provide RSA encoding/decoding support.
        final ResourceBundle bundle = ResourceBundle
                .getBundle(SETTINGS_BUNDLE_NAME);
        dto.setEmail(bundle.getString(EMAIL_PROPERTY));
        dto.setPassword(bundle.getString(PASSWORD_PROPERTY));
    }

}
