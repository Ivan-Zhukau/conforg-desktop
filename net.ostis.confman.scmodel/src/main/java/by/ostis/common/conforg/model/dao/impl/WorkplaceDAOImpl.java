package by.ostis.common.conforg.model.dao.impl;

import by.ostis.common.conforg.model.dao.WorkplaceDAO;
import by.ostis.common.conforg.model.dao.exception.DAOException;
import by.ostis.common.conforg.model.entity.Workplace;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;

public class WorkplaceDAOImpl extends BaseDAOImpl<Workplace> implements WorkplaceDAO {

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

    protected void saveFields(Workplace element, ScAddress addressNode) throws DAOException {
        ScString positionContent = ScStrings.wrap(element.getPosition());
        ScAddress positionNode = ScUtils.INSTANCE.createNodeWithContent(positionContent);
        ScUtils.INSTANCE.createRelation(addressNode, positionNode, ScChildRelations.POSITION);

        ScString workplaceContent = ScStrings.wrap(element.getWorkplace());
        ScAddress workplaceNode = ScUtils.INSTANCE.createNodeWithContent(workplaceContent);
        ScUtils.INSTANCE.createRelation(addressNode, workplaceNode, ScChildRelations.WORKPLACE);
    }

    protected Workplace readFields(ScAddress addressElement) throws DAOException {
        ScAddress workplaceAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.WORKPLACE);
        String workplaceContent = ScUtils.INSTANCE.findElementContent(workplaceAdr);

        ScAddress positionAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.POSITION);
        String positionContent = ScUtils.INSTANCE.findElementContent(positionAdr);

        return new Workplace(workplaceContent, positionContent);
    }
}
