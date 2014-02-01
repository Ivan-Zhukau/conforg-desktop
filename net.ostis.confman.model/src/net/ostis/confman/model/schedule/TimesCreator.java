package net.ostis.confman.model.schedule;

import java.util.Date;

import net.ostis.confman.services.common.model.FullModel;
import net.ostis.confman.services.common.model.SectionSettings;

public class TimesCreator {

    private Date      currentTime;

    static final long ONE_MINUTE_IN_MILLIS = 60000;

    public TimesCreator() {

    }

    void create(final FullModel model, final Schedule schedule) {

        setTimes(model, schedule);
    }

    private void setTimes(final FullModel model, final Schedule schedule) {

        for (final TimeEntity timeConference : schedule.getConferences()) {
            for (final TimeEntity timeSection : timeConference.getSons()) {
                final SectionSettings settings = getSectionSettings(
                        timeSection, model);
                this.currentTime = settings.getSection().getDate();
                for (final TimeEntity timeEntity : timeSection.getSons()) {
                    setTime(timeEntity, settings);
                }
            }
        }

    }

    private void setTime(final TimeEntity timeEntity,
            final SectionSettings settings) {

        timeEntity.setSrart(this.currentTime);

        if (timeEntity instanceof TimeChairmanWord) {
            addMinuts(settings.getChairmanTime());
        }

        if (timeEntity instanceof TimeReport) {
            addMinuts(settings.getReportTime());
        }

        if (timeEntity instanceof TimeBreak) {
            ;
            addMinuts(settings.getBreakTime());
        }

        if (timeEntity instanceof TimeCoffeeBreak) {
            ;
            addMinuts(settings.getCoffeeBreakTime());
        }
        timeEntity.setEnd(this.currentTime);
    }

    private void addMinuts(final int minuts) {

        final long t = this.currentTime.getTime();
        this.currentTime = new Date(t + (minuts * ONE_MINUTE_IN_MILLIS));
    }

    private SectionSettings getSectionSettings(final TimeEntity timeSection,
            final FullModel model) {

        for (final SectionSettings settings : model.getSectionSettings()) {
            if (settings.getSection().getTitle().equals(timeSection.getName())) {
                return settings;
            }
        }
        return null;
    }
}
