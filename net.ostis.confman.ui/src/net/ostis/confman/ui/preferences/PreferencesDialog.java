package net.ostis.confman.ui.preferences;

import java.io.IOException;

import net.ostis.confman.ui.net.NetworkConstants;

import org.apache.log4j.Logger;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.preference.PreferenceStore;

public class PreferencesDialog {

    private static final Logger LOGGER        = Logger.getLogger(PreferenceDialog.class);

    private static final String PROXY_PAGE_ID = "";

    private static final String EMAIL_PAGE_ID = "";

    public PreferencesDialog() {

        super();
        init();
    }

    private void init() {

        final PreferenceManager manager = new PreferenceManager();
        addNodes(manager);
        final PreferenceDialog preferenceDialog = new PreferenceDialog(null,
                manager);
        final PreferenceStore preferenceStore = createPreferenceStore();
        preferenceDialog.setPreferenceStore(preferenceStore);
        preferenceDialog.open();
        savePreferences(preferenceStore);
    }

    private void addNodes(final PreferenceManager manager) {

        final PreferenceNode proxyNode = new PreferenceNode(PROXY_PAGE_ID,
                new ProxyPreferencesPage());
        manager.addToRoot(proxyNode);
        final PreferenceNode emailNode = new PreferenceNode(EMAIL_PAGE_ID,
                new EmailPreferencesPage());
        manager.addToRoot(emailNode);
    }

    private PreferenceStore createPreferenceStore() {

        final PreferenceStore preferenceStore = new PreferenceStore(
                NetworkConstants.PROPERTIES_FILE_PATH);
        try {
            preferenceStore.load();
        } catch (final IOException e) {
            LOGGER.warn(e);
        }
        return preferenceStore;
    }

    private void savePreferences(final PreferenceStore preferenceStore) {

        try {
            preferenceStore.save();
        } catch (final IOException e) {
            LOGGER.error(e);
        }
    }

}
