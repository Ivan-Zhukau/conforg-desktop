package net.ostis.confman.model.dao.impl.scmemory;

enum ScSpaces implements ScIdentifiable {
    CONFERENCES("conforg_conferences_space"),
    SECTIONS("conforg_sections_space"),
    REPORTS("conforg_reports_space"),
    PARTICIPANTS("conforg_participants_space"),
    PERSONS("conforg_persons_space"),
    ADDRESS("conforg_address"),
    ACADEMIC_DEGREE("conforg_academic_degree"),
    WORKPLACE("conforg_workplace"),
    PARTICIPANT_ADDITIONAL_INFO("conforg_participant_additional_info"), 
    PARTICIPANT_ARRIVAL("conforg_participant_arrival"), 
    PARTICIPANT_ROLE("conforg_participant_role"), 
    PARTICIPATION_IN_CONFERENCE("conforg_participation_in_conference");

    private String spaceSystemId;

    ScSpaces(String spaceSystemId) {

        this.spaceSystemId = spaceSystemId;
    }

    public String getSystemId() {

        return spaceSystemId;
    }
}
