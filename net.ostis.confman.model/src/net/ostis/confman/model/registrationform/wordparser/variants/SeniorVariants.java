package net.ostis.confman.model.registrationform.wordparser.variants;

public enum SeniorVariants implements Variants {

    SENIOR("Сотрудник"),
    SENIOR_SMALL("сотрудник"),
    SENIOR_EN("Senior"),
    SENIOR_SMALL_EN("senior");

    private String academicTitle;

    private SeniorVariants(final String academicTitle) {

        this.academicTitle = academicTitle;
    }

    @Override
    public String getVariant() {

        return this.academicTitle;
    }
}
