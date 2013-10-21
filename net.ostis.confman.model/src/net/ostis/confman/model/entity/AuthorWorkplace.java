package net.ostis.confman.model.entity;

public class AuthorWorkplace {
	
    private String affliation;

    private String position;

    public AuthorWorkplace() {

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
