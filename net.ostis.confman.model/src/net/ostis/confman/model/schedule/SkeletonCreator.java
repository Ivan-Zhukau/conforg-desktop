package net.ostis.confman.model.schedule;

import net.ostis.confman.services.common.model.Conference;
import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.Report;
import net.ostis.confman.services.common.model.Section;
import net.ostis.confman.services.common.model.SectionSettings;


public class SkeletonCreator {
    
    public SkeletonCreator() {
    }
    
    void create(FullModel model, Schedule schedule){
        
        convertToSchedule(model, schedule);
        addCoffeeBreaks(model, schedule);
        addChairmanWords(schedule);
        addBreaks(schedule);
    }

    private void addBreaks(Schedule schedule) {

        for(TimeEntity timeConference : schedule.getConferences()){
            for(TimeEntity  timeSection :  timeConference.getSons()){
                int breakCount = timeSection.getSons().size()-1;
                for(int index=0; index < breakCount ; ++index){
                    timeSection.getSons().add(2*index+1, new TimeBreak());   
                }
            }
        }        
    }

    private void addCoffeeBreaks(FullModel model, Schedule schedule) {
        
        for(TimeEntity timeConference : schedule.getConferences()){
            for(TimeEntity  timeSection :  timeConference.getSons()){
                int coffeBreakNumber = getCoffeeBreakNumber(timeSection, model);
                int cycle = getCycle(timeSection.getSons().size(), coffeBreakNumber);
                for(int index = 1; index <= coffeBreakNumber; ++index ){
                    timeSection.getSons().add(index*cycle+index-1, new TimeCoffeeBreak());
                }
            }
        }        
    }

    private int getCycle(int sons, int coffeBreakNumber) {
        
        int cycle = sons;    
        if(coffeBreakNumber!=0){
            cycle = sons/(coffeBreakNumber+1);
        }
        else{
            return sons;
        }
        if(cycle<1){
            return 1;
        }
        return cycle;
    }

    private int getCoffeeBreakNumber(TimeEntity timeSection, FullModel model) { 
        
        for(SectionSettings settings : model.getSectionSettings()){
            if(settings.getSection().getTitle().equals(timeSection.getName())){
                if(settings.getCoffeeBreakNumber() >= timeSection.getSons().size()){
                    return timeSection.getSons().size()-1;
                }
                return settings.getCoffeeBreakNumber();
            }
        }
        return 0;
    }

    private void addChairmanWords(Schedule schedule) {
        
        for(TimeEntity timeConference : schedule.getConferences()){
            for(TimeEntity  timeSection :  timeConference.getSons()){
                timeSection.getSons().add(0, new TimeChairmanWord());
            }
        }
    }

    private void convertToSchedule(FullModel model, Schedule schedule) {
        
        for(Conference conference : model.getConferences()){
            TimeConference timeConference = new TimeConference();
            timeConference.setName(conference.getTitle());
            timeConference.setSrart(conference.getStartDate());
            schedule.getConferences().add(timeConference);
            setSections(conference, model, timeConference);
        }
    }

    private void setSections(Conference conference, FullModel model,
            TimeConference timeConference) {
        
        for(Section section : model.getSections()){
            if(section.getConference() != null && section.getConference().equals(conference)){
                TimeSection timeSection = new TimeSection(section.getTitle());
                timeSection.setSrart(section.getDate());
                timeConference.getSons().add(timeSection);
                setReports(section, model, timeSection);
            }
        }
    }

    private void setReports(Section section, FullModel model,
            TimeSection timeSection) {

        for(Report report : model.getReports()){
            if(report.getSection()!=null && report.getSection().equals(section)){
                timeSection.getSons().add(new TimeReport(report.getTitle()));
            }
        }        
    }
}
