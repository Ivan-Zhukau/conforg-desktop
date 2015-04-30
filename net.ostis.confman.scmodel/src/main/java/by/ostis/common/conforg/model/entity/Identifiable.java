package by.ostis.common.conforg.model.entity;

import java.util.UUID;

public interface Identifiable {

    UUID getSystemId();

    void setSystemId(UUID systemId);
}
