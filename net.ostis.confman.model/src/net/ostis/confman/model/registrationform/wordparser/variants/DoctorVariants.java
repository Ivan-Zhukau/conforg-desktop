package net.ostis.confman.model.registrationform.wordparser.variants;


public enum DoctorVariants implements Variants{
    
    DOCTOR("Доктор"),
    DOCTOR_SMALL("доктор"),
    DOCTOR_EN("Doctor"),
    DOCTOR_SMALL_EN("doctor");

    private String academicDegree;

    private DoctorVariants(final String academicDegree) {

        this.academicDegree = academicDegree;
    }

    @Override
    public String getVariant() {

        return this.academicDegree;
    }

}
