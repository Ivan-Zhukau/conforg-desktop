package net.ostis.confman.ui.common.component.util;

import net.ostis.confman.ui.common.Localizable;

public final class LocalizationUtil {

    private LocalizationUtil() {

        super();
    }

    public static String translate(final Localizable toTranslate) {

        return toTranslate.getResourceKey();
    }

}
