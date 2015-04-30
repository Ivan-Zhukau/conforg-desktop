package by.ostis.common.conforg.model.dao.impl;

import by.ostis.common.conforg.model.dao.ConferenceDAO;
import by.ostis.common.conforg.model.dao.exception.DAOException;
import by.ostis.common.conforg.model.entity.Conference;
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
