package net.ostis.confman.model.entity.scmemory;

import java.util.UUID;

public interface Identifiable {

    UUID getSystemId();

    void setSystemId(UUID systemId);
}
