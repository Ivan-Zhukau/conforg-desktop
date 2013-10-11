package net.ostis.confman.services.editing;

public class WorkplaceInformation {
	
	private String affliation;

    private String position;

    public WorkplaceInformation() {

        super();
    }

    public String getAffliation() {

        return this.affliation;
    }

    public String getPosition() {

        return this.position;
    }

    public void setAffliation(final String affliation) {

        this.affliation = affliation;
    }

    public void setPosition(final String position) {

        this.position = position;
    }
}
