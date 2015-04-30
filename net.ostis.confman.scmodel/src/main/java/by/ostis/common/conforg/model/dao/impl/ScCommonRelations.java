package by.ostis.common.conforg.model.dao.impl;

enum ScCommonRelations implements ScIdentifiable {

    ADDRESS("conforg_address*"),
    SECTION("conforg_section*"),
    CONFERENCE("conforg_conference*"),
    REPORT("conforg_report*"),
    PARTICIPANT("conforg_participant*");

    private String systemId;

    ScCommonRelations(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemId() {
        return systemId;
    }
}
