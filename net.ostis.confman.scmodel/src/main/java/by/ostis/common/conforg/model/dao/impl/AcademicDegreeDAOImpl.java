package by.ostis.common.conforg.model.dao.impl;

import by.ostis.common.conforg.model.dao.AcademicDegreeDAO;
import by.ostis.common.conforg.model.dao.exception.DAOException;
import by.ostis.common.conforg.model.entity.AcademicDegree;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;

public class AcademicDegreeDAOImpl extends BaseDAOImpl<AcademicDegree> implements AcademicDegreeDAO {

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

    public AcademicDegreeDAOImpl() {
        super(ScSpaces.ACADEMIC_DEGREE);
    }

    protected void saveFields(AcademicDegree element, ScAddress addressNode) throws DAOException {
        ScString degreeContent = ScStrings.wrap(element.getDegree());
        ScAddress degreeNode = ScUtils.INSTANCE.createNodeWithContent(degreeContent);
        ScUtils.INSTANCE.createRelation(addressNode, degreeNode, ScChildRelations.DEGREE);

        ScString titleContent = ScStrings.wrap(element.getTitle());
        ScAddress titleNode = ScUtils.INSTANCE.createNodeWithContent(titleContent);
        ScUtils.INSTANCE.createRelation(addressNode, titleNode, ScChildRelations.TITLE);
    }

    protected AcademicDegree readFields(ScAddress addressElement) throws DAOException {
        ScAddress degreeAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.DEGREE);
        String degreeContent = ScUtils.INSTANCE.findElementContent(degreeAdr);

        ScAddress titleAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.TITLE);
        String titleContent = ScUtils.INSTANCE.findElementContent(titleAdr);

        return new AcademicDegree(degreeContent, titleContent);
    }
}
