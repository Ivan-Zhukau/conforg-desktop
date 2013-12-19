package net.ostis.confman.model.registrationform.wordparser.variants;


public enum ProfessorVariants implements Variants{
          
    PROFESSOR("Профессор"),
    PROFESSOR_SMALL("профессор"),
    PROFESSOR_EN("Professor"),
    PROFESSOR_SMALL_EN("professor");

    private String academicTitle;

    private ProfessorVariants(final String academicTitle) {

        this.academicTitle = academicTitle;
    }

    @Override
    public String getVariant() {

        return this.academicTitle;
    }

}
