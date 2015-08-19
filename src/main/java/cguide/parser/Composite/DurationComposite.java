/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Composite;

import com.compguide.Persistence.Entities.Duration;
import com.compguide.Persistence.Entities.SessionBeans.DurationFacade;
import com.compguide.Persistence.Entities.SessionBeans.TemporalUnitFacade;
import com.compguide.Persistence.Entities.TemporalUnit;

/**
 *
 * @author António
 */
public class DurationComposite {

    private Duration duration;
    private TemporalUnit temporalUnit;
    private static com.compguide.Persistence.Entities.SessionBeans.DurationFacade ejbDurationFacade;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setDuration(Double minDurationValue, Double maxDurationValue,
            Double durationValue) {
        this.duration = new Duration(minDurationValue, maxDurationValue, durationValue);
    }

    public void setTemporalUnit(String value) {
        this.temporalUnit = new TemporalUnit(value);
        this.duration.setTemporalUnitID(temporalUnit);
    }

    private static DurationFacade getDurationFacade() {
        if (ejbDurationFacade == null) {
            ejbDurationFacade = new DurationFacade();
        }
        return ejbDurationFacade;
    }

    public void check() {
        checkDuration(duration);
    }

    public static Duration checkDuration(Duration durat) {
        Duration dur = null;

        if (durat.asExactValue()) {
            TemporalUnit temporalUnitID = TemporalUnitComposite.checkTemporalUnit(
                    durat.getTemporalUnitID()
            );

            durat.setTemporalUnitID(temporalUnitID);

            dur = getDurationFacade().findByDurationValueAndTemporalUnit(
                    temporalUnitID, durat.getDurationValue()
            );

        }
        if (durat.asInterval()) {
            TemporalUnit temporalUnitID = TemporalUnitComposite.checkTemporalUnit(
                    durat.getTemporalUnitID()
            );

            durat.setTemporalUnitID(temporalUnitID);

            dur = getDurationFacade().findByMinMaxValueAndTemporalUnit(temporalUnitID,
                    durat.getMinDurationValue(),
                    durat.getMaxDurationValue());

        }

        if (dur == null) {
            getDurationFacade().create(durat);
            dur = durat;
        }

        return dur;
    }

}
