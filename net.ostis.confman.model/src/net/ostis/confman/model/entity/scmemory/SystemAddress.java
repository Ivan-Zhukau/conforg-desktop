package net.ostis.confman.model.entity.scmemory;

import java.util.UUID;

import by.ostis.common.sctpclient.model.ScAddress;

public class SystemAddress {

    private ScAddress scAddress;

    private UUID      systemId;

    public SystemAddress(ScAddress scAddress) {

        super();
        this.scAddress = scAddress;
    }

    public SystemAddress(UUID systemId) {

        super();
        this.systemId = systemId;
    }

    public SystemAddress(ScAddress scAddress, UUID systemId) {

        super();
        this.scAddress = scAddress;
        this.systemId = systemId;
    }

    public ScAddress getScAddress() {

        return scAddress;
    }

    public UUID getSystemId() {

        return systemId;
    }

    @Override
    public String toString() {

        String scAdr = scAddress != null ? scAddress.getBytes().toString() : "not loaded";
        return "SystemAddress: [ScAddress: " + scAdr + ", UUID: " + systemId + "]";
    }

}
