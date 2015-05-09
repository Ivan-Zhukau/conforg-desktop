package net.ostis.confman.model.entity.scmemory;


abstract class BaseEntity implements Identifiable {

    protected SystemAddress systemAddress;

    public BaseEntity() {

        super();
    }

    public SystemAddress getSystemAddress() {

        return systemAddress;
    }

    public void setSystemAddress(SystemAddress systemAddress) {

        this.systemAddress = systemAddress;
    }
}
