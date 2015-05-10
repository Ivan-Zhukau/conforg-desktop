package net.ostis.confman.model.dao.impl.scmemory;

enum ScCommonRelations implements ScIdentifiable {

    ADDRESS("conforg_address*"),
    SECTION("conforg_section*"),
    CONFERENCE("conforg_conference*"),
    REPORT("conforg_report*"),
    PARTICIPANT("conforg_participant*"),
    WORKPLACE("conforg_workplace*"),
    PARTICIPANT_ARRIVAL("conforg_participant_arrival*"), 
    PARTICIPANT_ROLE("conforg_participant_role*"), 
    PARTICIPATION_IN_CONFERENCE("conforg_participation_in_conference*"),
    PERSON("conforg_person*"), 
    REPORTER("conforg_reporter*");

    private String systemId;

    ScCommonRelations(String systemId) {

        this.systemId = systemId;
    }

    @Override
    public String getSystemId() {

        return systemId;
    }
}
