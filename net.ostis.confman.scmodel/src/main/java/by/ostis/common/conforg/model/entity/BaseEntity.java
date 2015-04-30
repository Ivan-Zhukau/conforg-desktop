package by.ostis.common.conforg.model.entity;

import java.util.UUID;

/* (non-Javadoc)
 * BaseEntity 
 */
abstract class BaseEntity implements Identifiable {

    protected UUID systemId;

    public BaseEntity() {
        super();
    }

    public UUID getSystemId() {
        return systemId;
    }

    public void setSystemId(UUID systemId) {
        this.systemId = systemId;
    }
}
