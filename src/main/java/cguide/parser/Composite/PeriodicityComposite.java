/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Composite;

import com.compguide.Persistence.Entities.ConditionSet;
import com.compguide.Persistence.Entities.CyclePartDefinition;
import com.compguide.Persistence.Entities.CyclePartPeriodicity;
import com.compguide.Persistence.Entities.Duration;
import com.compguide.Persistence.Entities.Periodicity;
import com.compguide.Persistence.Entities.SessionBeans.PeriodicityFacade;
import com.compguide.Persistence.Entities.TemporalUnit;

/**
 *
 * @author António
 */
public class PeriodicityComposite {

    private Periodicity periodicity;
    private CyclePartDefinition cyclePartDefinition;
    private Duration duration;
    private TemporalUnit temporalUnit;
    private static com.compguide.Persistence.Entities.SessionBeans.PeriodicityFacade ejbPeriodicityFacade;

    public PeriodicityComposite() {
    }

    public void setCyclePartPeriodicity(Duration durationID, CyclePartPeriodicity cyclePartPeriodicityID) {
        this.cyclePartDefinition = new CyclePartDefinition(durationID, cyclePartPeriodicityID);
        this.periodicity.setCyclePartDefinitionID(cyclePartDefinition);
    }

    public void setPeriodicity(double periodicityValue, Integer repetitionValue) {
        this.periodicity = new Periodicity(periodicityValue, repetitionValue);
    }

    public void setDuration(Double minDurationValue, Double maxDurationValue,
            Double durationValue, TemporalUnit temporalDurationUnit) {
        this.duration = new Duration(minDurationValue, maxDurationValue, durationValue, temporalDurationUnit);
        this.periodicity.setDurationID(duration);
    }

    public void setPeriodicity(double periodicityValue) {
        this.periodicity = new Periodicity(periodicityValue);
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
        this.periodicity.setDurationID(duration);
    }

    public void setTemporalUnit(TemporalUnit temporalUnit) {
        this.temporalUnit = temporalUnit;
        this.periodicity.setTemporalUnitID(temporalUnit);
    }

    public void setCyclePartDefinition(CyclePartDefinition cyclePartDefinition) {
        this.cyclePartDefinition = cyclePartDefinition;
        this.periodicity.setCyclePartDefinitionID(cyclePartDefinition);
    }

    public void setTemporalUnit(String value) {
        this.temporalUnit = new TemporalUnit(value);
        this.periodicity.setTemporalUnitID(temporalUnit);
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    private static PeriodicityFacade getPeriodicityFacade() {
        if (ejbPeriodicityFacade == null) {
            ejbPeriodicityFacade = new PeriodicityFacade();
        }
        return ejbPeriodicityFacade;
    }

    public void check() {
        Periodicity per = checkPeriodicity(periodicity);
        
        periodicity = per;
    }

    public static Periodicity checkPeriodicity(Periodicity periodicity) {
        Periodicity aux = null;

        if (periodicity.asDuration()) {
            TemporalUnit temporalUnit = TemporalUnitComposite.checkTemporalUnit(
                    periodicity.getTemporalUnitID()
            );
            Duration dur = DurationComposite.checkDuration(
                    periodicity.getDurationID()
            );

            periodicity.setDurationID(dur);
            periodicity.setTemporalUnitID(temporalUnit);

            if (periodicity.getCyclePartDefinitionID() != null) {
                CyclePartDefinition partDefinition = CyclePartDefinitionComposite.checkCyclePartDefinition(
                        periodicity.getCyclePartDefinitionID()
                );

                periodicity.setCyclePartDefinitionID(partDefinition);

                aux = getPeriodicityFacade().
                        findByPeriodicityValueTemporalUnitDurationAndCyclePartDefinition(
                                partDefinition, dur,
                                temporalUnit, periodicity.getPeriodicityValue()
                        );
            }
        }

        if (periodicity.asRepetition()) {
            TemporalUnit temporalUnit = TemporalUnitComposite.checkTemporalUnit(
                    periodicity.getTemporalUnitID()
            );

            periodicity.setTemporalUnitID(temporalUnit);

            if (periodicity.getCyclePartDefinitionID() != null) {
                CyclePartDefinition partDefinition = CyclePartDefinitionComposite.checkCyclePartDefinition(
                        periodicity.getCyclePartDefinitionID()
                );

                periodicity.setCyclePartDefinitionID(partDefinition);

                aux = getPeriodicityFacade().
                        findByPeriodicityValueTemporalUnitRepetitonValueAndCyclePartDefinition(
                                partDefinition, periodicity.getRepetitionValue(),
                                temporalUnit, periodicity.getPeriodicityValue()
                        );
            }
        }

        if (aux == null) {
            getPeriodicityFacade().create(periodicity);
            aux = periodicity;

        }

        return periodicity;
    }

}
