package net.ostis.confman.model.registrationform.wordparser.variants;


public enum DocentVariants implements Variants{
    
    DOCENT("Доцент"),
    DOCENT_SMALL("доцент"),
    DOCENT_EN("Docent"),
    DOCENT_SMALL_EN("docent");

    private String academicTitle;

    private DocentVariants(final String academicTitle) {

        this.academicTitle = academicTitle;
    }

    @Override
    public String getVariant() {

        // TODO Auto-generated method stub
        return this.academicTitle;
    }
}
