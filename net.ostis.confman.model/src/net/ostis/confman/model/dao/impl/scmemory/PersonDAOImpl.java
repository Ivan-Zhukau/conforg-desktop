package net.ostis.confman.model.dao.impl.scmemory;

import java.util.List;

import net.ostis.confman.model.dao.PersonDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.Person;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;

public class PersonDAOImpl extends BaseDAOImpl<Person> implements PersonDAO {

    private enum ScChildRelations implements ScIdentifiable {

        FIRST_NAME("conforg_persons_first_name*"),
        PATRONYMIC("conforg_persons_patronymic*"),
        LAST_NAME("conforg_persons_last_name*"),
        WORKPLACE("conforg_persons_workplace*"),
        ACADEMIC_DEGREE("conforg_persons_academic_degree*");

        private String systemId;

        ScChildRelations(String systemId) {

            this.systemId = systemId;
        }

        public String getSystemId() {

            return systemId;
        }
    }

    public PersonDAOImpl() {

        super(ScSpaces.PERSONS);
    }

    @Override
    protected void saveFields(Person element, ScAddress parentNode)
            throws DAOException {

        ScString firstNameContent = ScStrings.wrap(element.getFirstName());
        ScAddress firstNameNode = ScUtils.INSTANCE
                .createNodeWithContent(firstNameContent);
        ScUtils.INSTANCE.createRelation(parentNode, firstNameNode,
                ScChildRelations.FIRST_NAME);

        ScString patronymicContent = ScStrings.wrap(element.getPatronymic());
        ScAddress patronymicNode = ScUtils.INSTANCE
                .createNodeWithContent(patronymicContent);
        ScUtils.INSTANCE.createRelation(parentNode, patronymicNode,
                ScChildRelations.PATRONYMIC);

        ScString lastNameContent = ScStrings.wrap(element.getSurname());
        ScAddress lastNameNode = ScUtils.INSTANCE
                .createNodeWithContent(lastNameContent);
        ScUtils.INSTANCE.createRelation(parentNode, lastNameNode,
                ScChildRelations.LAST_NAME);

        ScAddress addressNode = ScUtils.INSTANCE.findElement(element
                .getResidenceSystemAddress());
        ScUtils.INSTANCE.createRelation(parentNode, addressNode,
                ScCommonRelations.ADDRESS);

        ScAddress workplaceNode = ScUtils.INSTANCE.findElement(element
                .getWorkplaceSystemAddress());
        ScUtils.INSTANCE.createRelation(parentNode, workplaceNode,
                ScChildRelations.WORKPLACE);

        ScAddress academicDegreeNode = ScUtils.INSTANCE.findElement(element
                .getAcademicInfoSystemAddress());
        ScUtils.INSTANCE.createRelation(parentNode, academicDegreeNode,
                ScChildRelations.ACADEMIC_DEGREE);
    }

    @Override
    protected Person readFields(ScAddress elementNode) throws DAOException {

        ScAddress firstNameAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.FIRST_NAME);
        String firstNameContent = ScUtils.INSTANCE
                .findElementContent(firstNameAdr);

        ScAddress patronymicAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.PATRONYMIC);
        String patronymicContent = ScUtils.INSTANCE
                .findElementContent(patronymicAdr);

        ScAddress lastNameAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScChildRelations.LAST_NAME);
        String lastNameContent = ScUtils.INSTANCE
                .findElementContent(lastNameAdr);

        ScAddress addressAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(elementNode,
                        ScCommonRelations.ADDRESS);
        // TODO add get element's uuid when implemented and pass as constructor
        // parameter
        // UUID addressSystemId = ScUtils.INSTANCE.findElementUuid(addressAdr);
        return new Person(firstNameContent, patronymicContent, lastNameContent,
                null, null, null, null);
    }
}
