package net.ostis.confman.model.entity.scmemory;

public class WorkplaceInformation extends BaseEntity {

    private String workplace;

    private String position;

    public WorkplaceInformation() {

        super();
    }

    public WorkplaceInformation(String workplace, String position) {

        super();
        this.workplace = workplace;
        this.position = position;
    }

    public String getWorkplace() {

        return this.workplace;
    }

    public void setWorkplace(final String workplace) {

        this.workplace = workplace;
    }

    public String getPosition() {

        return this.position;
    }

    public void setPosition(final String position) {

        this.position = position;
    }
}
