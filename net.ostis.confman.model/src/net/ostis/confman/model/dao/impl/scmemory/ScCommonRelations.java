package net.ostis.confman.model.dao.impl.scmemory;

enum ScCommonRelations implements ScIdentifiable {

    ADDRESS("conforg_address*"),
    SECTION("conforg_section*"),
    CONFERENCE("conforg_conference*"),
    REPORT("conforg_report*"),
    PARTICIPANT("conforg_participant*"),
    WORKPLACE("conforg_workplace*");

    private String systemId;

    ScCommonRelations(String systemId) {

        this.systemId = systemId;
    }

    @Override
    public String getSystemId() {

        return systemId;
    }
}
