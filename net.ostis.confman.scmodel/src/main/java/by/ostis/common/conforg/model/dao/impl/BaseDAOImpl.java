package by.ostis.common.conforg.model.dao.impl;

import by.ostis.common.conforg.model.dao.BaseDAO;
import by.ostis.common.conforg.model.dao.exception.DAOException;
import by.ostis.common.conforg.model.dao.exception.TypeMismatchException;
import by.ostis.common.conforg.model.entity.Identifiable;
import by.ostis.common.sctpclient.model.ScAddress;
import by.ostis.common.sctpclient.utils.constants.ScElementType;

import java.util.UUID;

abstract class BaseDAOImpl<Type extends Identifiable> implements BaseDAO<Type> {

    private final ScSpaces space;

    public BaseDAOImpl(final ScSpaces space) {
        super();
        this.space = space;
    }

    public UUID save(Type element) throws DAOException {
        if (element.getSystemId() != null) {
            throw new DAOException("attempting to save object with non-empty system id");
        }
        final UUID systemId = UUID.randomUUID();
        ScAddress elementNode = ScUtils.INSTANCE.createElWithGivenSystemId(systemId);

        ScAddress addressSpaceNode = ScUtils.INSTANCE.findElement(space.getSystemId());
        ScUtils.INSTANCE.createArc(ScElementType.SC_TYPE_ARC_POS, addressSpaceNode, elementNode);

        saveFields(element, elementNode);

        element.setSystemId(systemId);
        return systemId;
    }

    protected abstract void saveFields(Type element, ScAddress parentNode) throws DAOException;

    public Type read(UUID systemId) throws DAOException {
        if (systemId == null) {
            throw new NullPointerException("cannot find element, system id is null");
        }
        ScAddress elementNode = ScUtils.INSTANCE.findElement(systemId);
        boolean convenientType = ScUtils.INSTANCE.belongsToSpace(elementNode, space);
        if (!convenientType) {
            throw new TypeMismatchException("element with given system id doesn't belong to address space, id: "
                    + systemId);
        }
        Type element = readFields(elementNode);
        element.setSystemId(systemId);
        return element;
    }

    protected abstract Type readFields(ScAddress elementNode) throws DAOException;
}
