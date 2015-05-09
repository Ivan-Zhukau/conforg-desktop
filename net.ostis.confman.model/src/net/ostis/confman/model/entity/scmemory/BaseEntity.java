package net.ostis.confman.model.entity.scmemory;

import java.util.UUID;

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
