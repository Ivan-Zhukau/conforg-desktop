package net.ostis.confman.model.dao.impl.scmemory;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import net.ostis.confman.model.dao.WorkplaceDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.WorkplaceInformation;

public class WorkplaceDAOImpl extends BaseDAOImpl<WorkplaceInformation>
        implements WorkplaceDAO {

    private enum ScChildRelations implements ScIdentifiable {

        POSITION("conforg_workplace_position*"),
        WORKPLACE("conforg_workplace_workplace*");

        private String systemId;

        ScChildRelations(String systemId) {

            this.systemId = systemId;
        }

        public String getSystemId() {

            return systemId;
        }
    }

    public WorkplaceDAOImpl() {

        super(ScSpaces.WORKPLACE);
    }

    protected void saveFields(WorkplaceInformation element,
            ScAddress addressNode) throws DAOException {

        ScString positionContent = ScStrings.wrap(element.getPosition());
        ScAddress positionNode = ScUtils.INSTANCE
                .createNodeWithContent(positionContent);
        ScUtils.INSTANCE.createRelation(addressNode, positionNode,
                ScChildRelations.POSITION);

        ScString workplaceContent = ScStrings.wrap(element.getWorkplace());
        ScAddress workplaceNode = ScUtils.INSTANCE
                .createNodeWithContent(workplaceContent);
        ScUtils.INSTANCE.createRelation(addressNode, workplaceNode,
                ScChildRelations.WORKPLACE);
    }

    protected WorkplaceInformation readFields(ScAddress addressElement)
            throws DAOException {

        ScAddress workplaceAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(addressElement,
                        ScChildRelations.WORKPLACE);
        String workplaceContent = ScUtils.INSTANCE
                .findElementContent(workplaceAdr);

        ScAddress positionAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(addressElement,
                        ScChildRelations.POSITION);
        String positionContent = ScUtils.INSTANCE
                .findElementContent(positionAdr);

        return new WorkplaceInformation(workplaceContent, positionContent);
    }
}
