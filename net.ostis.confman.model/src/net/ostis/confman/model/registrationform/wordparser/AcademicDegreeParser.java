package net.ostis.confman.model.registrationform.wordparser;

import net.ostis.confman.model.registrationform.wordparser.academic.AcademicDegree;
import net.ostis.confman.model.registrationform.wordparser.variants.CandidateVariants;
import net.ostis.confman.model.registrationform.wordparser.variants.DoctorVariants;

public class AcademicDegreeParser {

    public AcademicDegreeParser() {

    }

    public String parse(final String info) {

        for (final DoctorVariants variant : DoctorVariants.values()) {
            if (info.contains(variant.getVariant())) {
                return AcademicDegree.DOCTOR.getValue();
            }
        }

        for (final CandidateVariants variant : CandidateVariants.values()) {
            if (info.contains(variant.getVariant())) {
                return AcademicDegree.CANDIDATE.getValue();
            }
        }
        return "none";
    }
}
