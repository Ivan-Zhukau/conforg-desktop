package net.ostis.confman.model.dao.impl.scmemory;

import net.ostis.confman.model.dao.PersonDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.Person;
import net.ostis.confman.model.entity.scmemory.SystemAddress;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;

public class PersonDAOImpl extends BaseDAOImpl<Person> implements PersonDAO {

    private enum ScChildRelations implements ScIdentifiable {

        FIRST_NAME("conforg_persons_first_name*"),
        PATRONYMIC("conforg_persons_patronymic*"),
        LAST_NAME("conforg_persons_last_name*"),
        ACADEMIC_DEGREE("conforg_persons_academic_degree*"),
        WORKPLACE("conforg_persons_workplace*"),
        CONTACT_INFO("conforg_persons_contacts*");

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

        ScAddress contactsNode = ScUtils.INSTANCE.findElement(element
                .getContactSystemAddress());
        ScUtils.INSTANCE.createRelation(parentNode, contactsNode,
                ScChildRelations.CONTACT_INFO);
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

        SystemAddress residenceSysAdr = loadLinkedElementSysAdr(elementNode,
                ScCommonRelations.ADDRESS);
        SystemAddress workplaceSysAdr = loadLinkedElementSysAdr(elementNode,
                ScChildRelations.WORKPLACE);
        SystemAddress contactsSysAdr = loadLinkedElementSysAdr(elementNode,
                ScChildRelations.CONTACT_INFO);
        SystemAddress academicInfoSysAdr = loadLinkedElementSysAdr(elementNode,
                ScChildRelations.ACADEMIC_DEGREE);

        return new Person(firstNameContent, patronymicContent, lastNameContent,
                residenceSysAdr, workplaceSysAdr, contactsSysAdr,
                academicInfoSysAdr);
    }
}
