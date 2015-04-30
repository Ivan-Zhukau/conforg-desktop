package by.ostis.common.conforg.model.dao.impl;

import by.ostis.common.conforg.model.dao.AddressDAO;
import by.ostis.common.conforg.model.dao.exception.DAOException;
import by.ostis.common.conforg.model.entity.Address;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.model.ScString;

public class AddressDAOImpl extends BaseDAOImpl<Address> implements AddressDAO {

    private enum ScChildRelations implements ScIdentifiable {

        COUNTRY("conforg_address_country*"),
        CITY("conforg_address_city*"),
        STREET("conforg_address_street*"),
        HOUSE_NUMBER("conforg_address_house_number*");

        private String systemId;

        ScChildRelations(String systemId) {
            this.systemId = systemId;
        }

        public String getSystemId() {
            return systemId;
        }
    }

    public AddressDAOImpl() {
        super(ScSpaces.ADDRESS);
    }

    @Override
    protected void saveFields(Address element, ScAddress addressNode) throws DAOException {
        ScString countryContent = ScStrings.wrap(element.getCountry());
        ScAddress countryNode = ScUtils.INSTANCE.createNodeWithContent(countryContent);
        ScUtils.INSTANCE.createRelation(addressNode, countryNode, ScChildRelations.COUNTRY);

        ScString cityContent = ScStrings.wrap(element.getCity());
        ScAddress cityNode = ScUtils.INSTANCE.createNodeWithContent(cityContent);
        ScUtils.INSTANCE.createRelation(addressNode, cityNode, ScChildRelations.CITY);

        ScString streetContent = ScStrings.wrap(element.getStreet());
        ScAddress streetNode = ScUtils.INSTANCE.createNodeWithContent(streetContent);
        ScUtils.INSTANCE.createRelation(addressNode, streetNode, ScChildRelations.STREET);

        ScString houseNumberContent = ScStrings.wrap(element.getHouseNumber());
        ScAddress houseNumberNode = ScUtils.INSTANCE.createNodeWithContent(houseNumberContent);
        ScUtils.INSTANCE.createRelation(addressNode, houseNumberNode, ScChildRelations.HOUSE_NUMBER);
    }

    @Override
    protected Address readFields(ScAddress addressElement) throws DAOException {
        ScAddress countryAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.COUNTRY);
        String countryContent = ScUtils.INSTANCE.findElementContent(countryAdr);

        ScAddress cityAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.CITY);
        String cityContent = ScUtils.INSTANCE.findElementContent(cityAdr);

        ScAddress streetAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.STREET);
        String streetContent = ScUtils.INSTANCE.findElementContent(streetAdr);

        ScAddress houseNumberAdr = ScUtils.INSTANCE.findUniqueElementByParentAndRelation(addressElement,
                ScChildRelations.HOUSE_NUMBER);
        String houseNumberContent = ScUtils.INSTANCE.findElementContent(houseNumberAdr);

        return new Address(countryContent, cityContent, streetContent, houseNumberContent);
    }
}
