package net.ostis.confman.ui.common.component.util;

import java.util.ResourceBundle;

import net.ostis.confman.ui.common.Localizable;

public final class LocalizationUtil {

    public static final String      LOCALISATION_BUNDLE_NAME = "resourses/MessagesBundle";

    private static LocalizationUtil INSTANCE;

    private static ResourceBundle   bundle;

    private LocalizationUtil() {

        super();
    }

    public static LocalizationUtil getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new LocalizationUtil();
            bundle = ResourceBundle.getBundle(LOCALISATION_BUNDLE_NAME);
        }
        return INSTANCE;
    }

    public String translate(final Localizable toTranslate) {

        return bundle.getString(toTranslate.getResourceKey());
    }

}
