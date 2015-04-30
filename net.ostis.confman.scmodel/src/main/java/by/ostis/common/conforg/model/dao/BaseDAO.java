package by.ostis.common.conforg.model.dao;

import by.ostis.common.conforg.model.dao.exception.DAOException;
import by.ostis.common.conforg.model.entity.Identifiable;

import java.util.UUID;

public interface BaseDAO<Type extends Identifiable> {

    /**
     * Performs save operation.
     * @param element element to be stored.
     * @return system identifier of stored element.
     * @throws DAOException if system identifier of @param element is non-empty.
     */
    UUID save(Type element) throws DAOException;

    /**
     * Performs read operation.
     * @param systemId system identifier.
     * @return element with nearby neighborhood loaded.
     * @throws DAOException if element with given system id wasn't found.
     */
    Type read(UUID systemId) throws DAOException;
}
