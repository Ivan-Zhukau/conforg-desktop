package net.ostis.confman.model.registrationform.wordparser;

import net.ostis.confman.model.registrationform.wordparser.academic.AcademicTitle;
import net.ostis.confman.model.registrationform.wordparser.variants.DocentVariants;
import net.ostis.confman.model.registrationform.wordparser.variants.ProfessorVariants;
import net.ostis.confman.model.registrationform.wordparser.variants.SeniorVariants;

public class AcademicTitleParser {

    public AcademicTitleParser() {

    }

    public String parse(final String info) {

        for (final DocentVariants variant : DocentVariants.values()) {
            if (info.contains(variant.getVariant())) {
                return AcademicTitle.DOCENT.getValue();
            }
        }

        for (final ProfessorVariants variant : ProfessorVariants.values()) {
            if (info.contains(variant.getVariant())) {
                return AcademicTitle.PROFESSOR.getValue();
            }
        }

        for (final SeniorVariants variant : SeniorVariants.values()) {
            if (info.contains(variant.getVariant())) {
                return AcademicTitle.SENIOR.getValue();
            }
        }
        return "none";
    }
}
