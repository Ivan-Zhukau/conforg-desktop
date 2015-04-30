package by.ostis.common.conforg.model.dao.impl;

enum ScSpaces implements ScIdentifiable {
    CONFERENCES("conforg_conferences_space"),
    SECTIONS("conforg_sections_space"),
    REPORTS("conforg_reports_space"),
    PARTICIPANTS("conforg_participants_space"),
    PERSONS("conforg_persons_space"),
    ADDRESS("conforg_address"),
    ACADEMIC_DEGREE("conforg_academic_degree"),
    WORKPLACE("conforg_workplace"),
    PARTICIPANT_ADDITIONAL_INFO("conforg_participant_additional_info");

    private String spaceSystemId;

    ScSpaces(String spaceSystemId) {
        this.spaceSystemId = spaceSystemId;
    }

    public String getSystemId() {
        return spaceSystemId;
    }
}
