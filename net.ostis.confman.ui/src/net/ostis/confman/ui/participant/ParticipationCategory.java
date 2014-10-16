package net.ostis.confman.ui.participant;

import net.ostis.confman.ui.common.Localizable;

public enum ParticipationCategory implements Localizable {

    DEFAULT("defaultComboItem"),
    PROGRAM_COMMITTEE("programCommittee"),
    VIP("vip"),
    ORDINARY_PARTISIPANT("ordinaryParticipant"),
    NEW_PARTISIPANT("newParticipant"),
    ORG_COMMITTEE("orgCommittee"),
    OFFICIAL_ORG_COMMITTEE("officialOrgCommittee"),
    STUD_ORG_COMMITTEE("studOrgCommittee"),
    INVITED("invited"),
    REPRESENTATIVE_OF_COORGANIZER("representativeOfCoOrganizer");

    private String rk;

    private ParticipationCategory(final String rk) {

        this.rk = rk;
    }

    public String getValue() {

        return this.rk;
    }

    @Override
    public String getResourceKey() {

        return this.rk;
    }
}
