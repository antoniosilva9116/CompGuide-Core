/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Composite;

import com.compguide.Persistence.Entities.ConditionSet;
import com.compguide.Persistence.Entities.CyclePartPeriodicity;
import com.compguide.Persistence.Entities.Duration;
import com.compguide.Persistence.Entities.SessionBeans.CyclePartPeriodicityFacade;
import com.compguide.Persistence.Entities.TemporalUnit;

/**
 *
 * @author António
 */
public class CyclePartPeriodicityComposite {

    private CyclePartPeriodicity cyclePartPeriodicity;
    private Duration duration;
    private TemporalUnit temporalUnit;
    private ConditionSet conditionSet;
    private static com.compguide.Persistence.Entities.SessionBeans.CyclePartPeriodicityFacade ejbCyclePartPeriodicityFacade;

    public CyclePartPeriodicityComposite() {
        cyclePartPeriodicity = new CyclePartPeriodicity();
    }

    private static CyclePartPeriodicityFacade getCyclePartPeriodicityFacade() {
        if (ejbCyclePartPeriodicityFacade == null) {
            ejbCyclePartPeriodicityFacade = new CyclePartPeriodicityFacade();
        }
        return ejbCyclePartPeriodicityFacade;
    }

    public CyclePartPeriodicity getCyclePartPeriodicity() {
        return cyclePartPeriodicity;
    }
    
    public void setDuration(Double minDurationValue, Double maxDurationValue,
            Double durationValue, TemporalUnit temporalDurationUnit) {
        this.duration = new Duration(minDurationValue, maxDurationValue, durationValue, temporalDurationUnit);
        this.cyclePartPeriodicity.setDurationID(duration);
    }

    public void setConditionSet(String identifier) {
        this.conditionSet = new ConditionSet(identifier);
        cyclePartPeriodicity.setConditionSetID(conditionSet);
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
        this.cyclePartPeriodicity.setDurationID(duration);
    }

    public void setTemporalUnit(String value) {
        this.temporalUnit = new TemporalUnit(value);
    }

    public void setCyclePartPeriodicity(double periodicityValue) {
        this.cyclePartPeriodicity = new CyclePartPeriodicity(periodicityValue);
    }
   
    public void setCyclePartPeriodicity(Integer repetitionValue, double periodicityValue) {
        this.cyclePartPeriodicity = new CyclePartPeriodicity(periodicityValue, repetitionValue);
    }

    public void check() {
        CyclePartPeriodicity partPeriodicity = checkCyclePartPeriodicity(cyclePartPeriodicity);
        cyclePartPeriodicity = partPeriodicity;
    }

    public static CyclePartPeriodicity checkCyclePartPeriodicity(CyclePartPeriodicity cyclePartPeriodicity) {
        CyclePartPeriodicity cpp = null;

        if (cyclePartPeriodicity.asDuration()) {
            TemporalUnit temporalUnitID = TemporalUnitComposite.checkTemporalUnit(
                    cyclePartPeriodicity.getTemporalUnitID()
            );

            Duration dur = DurationComposite.checkDuration(cyclePartPeriodicity.getDurationID());

            cyclePartPeriodicity.setDurationID(dur);
            cyclePartPeriodicity.setTemporalUnitID(temporalUnitID);

            cpp = getCyclePartPeriodicityFacade().findByPeriodicityValueTemporalUnitAndDurationID(
                    dur,
                    temporalUnitID,
                    cyclePartPeriodicity.getPeriodicityValue()
            );
        }

        if (cyclePartPeriodicity.asRepetition()) {
            TemporalUnit temporalUnitID = TemporalUnitComposite.checkTemporalUnit(
                    cyclePartPeriodicity.getTemporalUnitID()
            );

            cyclePartPeriodicity.setTemporalUnitID(temporalUnitID);

            cpp = getCyclePartPeriodicityFacade().findByPeriodicityValueTemporalUnitAndRepetitonValue(
                    cyclePartPeriodicity.getRepetitionValue(),
                    temporalUnitID,
                    cyclePartPeriodicity.getPeriodicityValue()
            );

        }

        if (cpp == null) {
            getCyclePartPeriodicityFacade().create(cyclePartPeriodicity);
            cpp = cyclePartPeriodicity;
        }

        return cpp;
    }

}
