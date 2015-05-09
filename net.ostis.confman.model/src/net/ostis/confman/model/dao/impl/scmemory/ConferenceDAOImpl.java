package net.ostis.confman.model.dao.impl.scmemory;

import net.ostis.confman.model.dao.ConferenceDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.Conference;
import by.ostis.common.sctpclient.model.ScAddress;

/* (non-Javadoc)
 * ConferenceDAO 
 */
public class ConferenceDAOImpl extends BaseDAOImpl<Conference> implements ConferenceDAO {

    private enum ScChildRelations {
        TITLE("conforg_conferences_title*"),
        START_DATE("conforg_conferences_start_date*"),
        END_DATE("conforg_conferences_end_date*");

        private String systemId;

        ScChildRelations(String systemId) {
            this.systemId = systemId;
        }

        public String getSystemId() {
            return systemId;
        }
    }

    public ConferenceDAOImpl() {
        super(ScSpaces.CONFERENCES);
    }

    @Override
    protected void saveFields(Conference element, ScAddress parentNode) throws DAOException {
        throw new IllegalStateException("not implemented yet");
    }

    @Override
    protected Conference readFields(ScAddress elementNode) throws DAOException {
        throw new IllegalStateException("not implemented yet");
    }
}
