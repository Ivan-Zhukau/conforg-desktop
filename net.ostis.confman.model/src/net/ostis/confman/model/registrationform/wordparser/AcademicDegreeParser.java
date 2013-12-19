package net.ostis.confman.model.registrationform.wordparser;

import net.ostis.confman.model.registrationform.wordparser.academic.AcademicDegree;
import net.ostis.confman.model.registrationform.wordparser.variants.*;

public class AcademicDegreeParser {
    
    public AcademicDegreeParser() {
    }
    
    public String parse(String info){
        for(DoctorVariants variant: DoctorVariants.values()){
            if(info.contains(variant.getVariant())){
                return AcademicDegree.DOCTOR.getValue();
            }
        }

        for(CandidateVariants variant: CandidateVariants.values()){
            if(info.contains(variant.getVariant())){
                return AcademicDegree.CANDIDATE.getValue();
            }
        }
        return "none";
    }
}
