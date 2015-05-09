package net.ostis.confman.model.dao.impl.scmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.utils.constants.ScElementType;
import net.ostis.confman.model.dao.BaseDAO;
import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.dao.exception.TypeMismatchException;
import net.ostis.confman.model.entity.scmemory.Identifiable;
import net.ostis.confman.model.entity.scmemory.SystemAddress;

abstract class BaseDAOImpl<Type extends Identifiable> implements BaseDAO<Type> {

    private final ScSpaces space;

    public BaseDAOImpl(final ScSpaces space) {

        super();
        this.space = space;
    }

    public UUID save(Type element) throws DAOException {

        if (element.getSystemAddress() != null) {
            throw new DAOException(
                    "attempting to save object with non-empty system id");
        }
        final UUID systemId = UUID.randomUUID();
        ScAddress elementNode = ScUtils.INSTANCE
                .createElWithGivenSystemId(systemId);

        ScAddress spaceNode = ScUtils.INSTANCE.findElement(space.getSystemId());
        ScUtils.INSTANCE.createArc(ScElementType.SC_TYPE_ARC_POS, spaceNode,
                elementNode);

        saveFields(element, elementNode);

        SystemAddress systemAddress = new SystemAddress(elementNode, systemId);
        element.setSystemAddress(systemAddress);
        return systemId;
    }

    protected abstract void saveFields(Type element, ScAddress parentNode)
            throws DAOException;

    public Type read(SystemAddress systemAddress) throws DAOException {

        if (systemAddress == null) {
            throw new NullPointerException(
                    "cannot find element, system id is null");
        }
        ScAddress elementNode = systemAddress.getScAddress();
        if (elementNode == null) {
            elementNode = ScUtils.INSTANCE.findElement(systemAddress);
        }
        boolean convenientType = ScUtils.INSTANCE.belongsToSpace(elementNode,
                space);
        if (!convenientType) {
            throw new TypeMismatchException(
                    "element with given system id doesn't belong to address space, id: "
                            + systemAddress);
        }
        Type element = readFields(elementNode);
        element.setSystemAddress(systemAddress);
        return element;
    }

    protected abstract Type readFields(ScAddress elementNode)
            throws DAOException;

    @Override
    public List<Type> readAll() throws DAOException {

        ScAddress spaceNode = ScUtils.INSTANCE.findElement(space.getSystemId());
        List<ScAddress> spaceElementAdrs = ScUtils.INSTANCE
                .getSpaceElements(spaceNode);
        List<Type> spaceElements = new ArrayList<>(spaceElementAdrs.size());
        for (ScAddress spaceElementAdr : spaceElementAdrs) {
            Type spaceElement = readFields(spaceElementAdr);
            spaceElements.add(spaceElement);
        }
        return spaceElements;
    }

    protected SystemAddress loadLinkedElementSysAdr(ScAddress parentNode,
            ScIdentifiable relationSysId) throws DAOException {

        ScAddress contactsAdr = ScUtils.INSTANCE
                .findUniqueElementByParentAndRelation(parentNode, relationSysId);
        return new SystemAddress(contactsAdr);
    }
    
    protected List<SystemAddress> loadLinkedElementsByRel(ScAddress parentNode,
            ScIdentifiable relationSysId) throws DAOException {
        String systemId = relationSysId.getSystemId();
        ScAddress relAdr = ScUtils.INSTANCE.findElement(systemId);
        List<ScAddress> elementAdrs = ScUtils.INSTANCE.getElementsLinkedByRelation(parentNode, relAdr);
        List<SystemAddress> elementSysAdrs = new ArrayList<SystemAddress>(elementAdrs.size());
        for (ScAddress scAddress : elementAdrs) {
            SystemAddress systemAddress = new SystemAddress(scAddress);
            elementSysAdrs.add(systemAddress);
        }
        return elementSysAdrs;
    }

}
