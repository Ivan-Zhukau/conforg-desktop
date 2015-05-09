package net.ostis.confman.model.dao.impl.scmemory;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;
import net.ostis.confman.model.dao.AcademicInfoDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.AcademicInformation;

public class AcademicInfoDAOImpl extends BaseDAOImpl<AcademicInformation>
        implements AcademicInfoDAO {

    private enum ScChildRelations implements ScIdentifiable {

        DEGREE("conforg_academic_degree_degree*"),
        TITLE("conforg_academic_degree_title*");

        private String systemId;

        ScChildRelations(String systemId) {

            this.systemId = systemId;
        }

        public String getSystemId() {

            return systemId;
        }
    }

    public AcademicInfoDAOImpl() {

        super(ScSpaces.ACADEMIC_DEGREE);
    }

    protected void saveFields(AcademicInformation element, ScAddress addressNode)
            throws DAOException {

        ScString degreeContent = ScStrings.wrap(element.getDegree());
        ScAddress degreeNode = ScUtils.INSTANCE
                .createNodeWithContent(degreeContent);
        ScUtils.INSTANCE.createRelation(addressNode, degreeNode,
                ScChildRelations.DEGREE);

        ScString titleContent = ScStrings.wrap(element.getTitle());
        ScAddress titleNode = ScUtils.INSTANCE
                .createNodeWithContent(titleContent);
        ScUtils.INSTANCE.createRelation(addressNode, titleNode,
                ScChildRelations.TITLE);
    }

    protected AcademicInformation readFields(ScAddress addressElement)
            throws DAOException {

        ScAddress degreeAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(addressElement,
                        ScChildRelations.DEGREE);
        String degreeContent = ScUtils.INSTANCE.findElementContent(degreeAdr);

        ScAddress titleAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(addressElement,
                        ScChildRelations.TITLE);
        String titleContent = ScUtils.INSTANCE.findElementContent(titleAdr);

        return new AcademicInformation(titleContent, degreeContent);
    }
}
