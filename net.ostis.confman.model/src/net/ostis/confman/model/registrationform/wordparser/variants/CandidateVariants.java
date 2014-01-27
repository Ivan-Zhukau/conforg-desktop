package net.ostis.confman.model.registrationform.wordparser.variants;

public enum CandidateVariants implements Variants {

    CANDIDATE("Кандидат"),
    CANDIDATE_SMALL("кандидат"),
    CANDIDATE_EN("Candidate"),
    CANDIDATE_SMALL_EN("candidate");

    private String academicDegree;

    private CandidateVariants(final String academicDegree) {

        this.academicDegree = academicDegree;
    }

    @Override
    public String getVariant() {

        return this.academicDegree;
    }

}
