package net.ostis.confman.model.dao;

import java.util.List;
import java.util.UUID;

import net.ostis.confman.model.dao.exception.DAOException;
import net.ostis.confman.model.entity.scmemory.Identifiable;
import net.ostis.confman.model.entity.scmemory.SystemAddress;

public interface BaseDAO<Type extends Identifiable> {

    /**
     * Performs save operation.
     * 
     * @param element
     *            element to be stored.
     * @return system identifier of stored element.
     * @throws DAOException
     *             if system identifier of @param element is non-empty.
     */
    UUID save(Type element) throws DAOException;

    /**
     * Performs read operation.
     * 
     * @param systemId
     *            system identifier.
     * @return element with nearby neighborhood loaded.
     * @throws DAOException
     *             if element with given system id wasn't found.
     */
    Type read(SystemAddress systemAddress) throws DAOException;

    List<Type> readAll() throws DAOException;
}
