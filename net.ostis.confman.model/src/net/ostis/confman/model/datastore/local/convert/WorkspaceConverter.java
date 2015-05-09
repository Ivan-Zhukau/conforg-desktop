package net.ostis.confman.model.datastore.local.convert;

import java.util.ArrayList;
import java.util.List;

import net.ostis.confman.model.datastore.util.IDProvider;
import net.ostis.confman.model.entity.xml.ConferenceViewState;
import net.ostis.confman.model.entity.xml.Workspace;
import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.FullModel;


public class WorkspaceConverter {

    public static Workspace convert(final FullModel model,
            final IDProvider idProvider) {

        final Workspace workspaceToStore = convertWorkspace(
                model.getWorkspace(), idProvider);
        return workspaceToStore;
    }

    private static Workspace convertWorkspace(
            net.ostis.confman.services.common.model.Workspace workspace,
            IDProvider idProvider) {

        ConferenceViewState conferenceViewState =
                convertConferenceViewState(workspace.getConferencePartState(), idProvider);
        Workspace workspaceToStore = new Workspace();
        workspaceToStore.setConferencePartState(conferenceViewState);
        return workspaceToStore;
    }

    private static ConferenceViewState convertConferenceViewState(
            net.ostis.confman.services.common.model.ConferenceViewState conferenceViewState,
            IDProvider idProvider) {

        List<Conference> openedConferences = conferenceViewState.getOpenedConferences();
        List<Long> serializedConferenceIds = new ArrayList<Long>(openedConferences.size());
        for (Conference openedConference : openedConferences) {
            serializedConferenceIds.add(idProvider.getId(openedConference));
        }
        ConferenceViewState serializedViewState = new ConferenceViewState();
        serializedViewState.setOpenedConferences(serializedConferenceIds);
        return serializedViewState;
    }
}
