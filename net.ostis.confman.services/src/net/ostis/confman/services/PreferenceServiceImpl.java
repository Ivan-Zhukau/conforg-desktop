package net.ostis.confman.services;

import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;

class PreferenceServiceImpl implements PreferenceService {

    private enum ProxyPreferences {

        PROXY_NODE("net.ostis.confman.preferences.proxy");

        private String value;

        private ProxyPreferences(final String value) {

            this.value = value;
        }

        public String value() {

            return this.value;
        }
    }

    private enum EmailPreferences {

        EMAIL_NODE("net.ostis.confman.preferences.email");

        private String value;

        private EmailPreferences(final String value) {

            this.value = value;
        }

        public String value() {

            return this.value;
        }
    }

    private ISecurePreferences securePreferences;

    public PreferenceServiceImpl() {

        super();
        init();
    }

    private void init() {

        this.securePreferences = SecurePreferencesFactory.getDefault();
    }

    @Override
    public ProxyPreferencesDto getProxyPreferences() {

        final ISecurePreferences proxyNode = this.securePreferences
                .node(ProxyPreferences.PROXY_NODE.value());
        return parseProxyNode(proxyNode);
    }

    private ProxyPreferencesDto parseProxyNode(
            final ISecurePreferences proxyNode) {

        final ProxyPreferencesDto proxyPreferencesDto = new ProxyPreferencesDto();
        // TODO kfs: provide smth...
        return proxyPreferencesDto;
    }

    @Override
    public void saveProxyPreferences(
            final ProxyPreferencesDto proxyPreferencesDto) {

        final ISecurePreferences proxyNode = this.securePreferences
                .node(ProxyPreferences.PROXY_NODE.value());
    }

    @Override
    public EmailPreferencesDto getEmailPreferences() {

        final ISecurePreferences emailNode = this.securePreferences
                .node(EmailPreferences.EMAIL_NODE.value());
        return parseEmailNode(emailNode);
    }

    private EmailPreferencesDto parseEmailNode(
            final ISecurePreferences emailNode) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveEmailPreferenses(
            final EmailPreferencesDto emailPreferencesDto) {

        final ISecurePreferences emailNode = this.securePreferences
                .node(EmailPreferences.EMAIL_NODE.value());
    }

}
