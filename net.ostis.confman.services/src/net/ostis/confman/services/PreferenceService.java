package net.ostis.confman.services;

public interface PreferenceService {

    ProxyPreferencesDto getProxyPreferences();

    void saveProxyPreferences(ProxyPreferencesDto proxyPreferencesDto);

    EmailPreferencesDto getEmailPreferences();

    void saveEmailPreferenses(EmailPreferencesDto emailPreferencesDto);

}
