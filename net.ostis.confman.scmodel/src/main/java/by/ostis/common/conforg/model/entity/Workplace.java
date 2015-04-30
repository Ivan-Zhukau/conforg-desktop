package by.ostis.common.conforg.model.entity;

public class Workplace extends BaseEntity{

    private String position;
    private String workplace;

    public Workplace() {
        super();
    }

    public Workplace(String position, String workplace) {
        super();
        this.position = position;
        this.workplace = workplace;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }
}
