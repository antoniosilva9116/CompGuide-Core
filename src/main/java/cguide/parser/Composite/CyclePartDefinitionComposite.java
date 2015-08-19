/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Composite;

import com.compguide.Persistence.Entities.*;
import com.compguide.Persistence.Entities.SessionBeans.CyclePartDefinitionFacade;
import com.compguide.Persistence.Entities.SessionBeans.CyclePartPeriodicityFacade;
import com.compguide.Persistence.Entities.SessionBeans.DurationFacade;
import com.compguide.Persistence.Entities.SessionBeans.TemporalUnitFacade;

/**
 *
 * @author António
 */
public class CyclePartDefinitionComposite {

    private CyclePartDefinition cyclePartDefinition;
    private Duration duration;
    private CyclePartPeriodicity cyclePartPeriodicity;
    private static com.compguide.Persistence.Entities.SessionBeans.CyclePartDefinitionFacade ejbCyclePartDefinitionFacade;

    public CyclePartDefinitionComposite() {
    }

    public CyclePartDefinition getCyclePartDefinition() {
        return cyclePartDefinition;
    }

    public void setCyclePartDefinition(CyclePartDefinition cyclePartDefinition) {
        this.cyclePartDefinition = cyclePartDefinition;
    }

    private static CyclePartDefinitionFacade getCyclePartDefinitionFacade() {
        if (ejbCyclePartDefinitionFacade == null) {
            ejbCyclePartDefinitionFacade = new CyclePartDefinitionFacade();
        }
        return ejbCyclePartDefinitionFacade;
    }

    public void setDuration(Double minDurationValue, Double maxDurationValue,
            Double durationValue, TemporalUnit temporalDurationUnit) {
        this.duration = new Duration(minDurationValue, maxDurationValue, durationValue, temporalDurationUnit);
        this.cyclePartDefinition.setDurationID(duration);

    }
    
    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
        this.cyclePartDefinition.setDurationID(duration);
    }

    public CyclePartPeriodicity getCyclePartPeriodicity() {
        return cyclePartPeriodicity;
    }

    public void setCyclePartPeriodicity(CyclePartPeriodicity cyclePartPeriodicity) {
        this.cyclePartPeriodicity = cyclePartPeriodicity;
        this.cyclePartDefinition.setCyclePartPeriodicityID(cyclePartPeriodicity);
    }

    public void setCyclePartPeriodicity(Integer repetitionValue, Double periodicityValue,
            Double minCycleDurationValue, Double maxCycleDurationValue,
            Double durationCycleValue, TemporalUnit temporalCycleDurationUnit,
            TemporalUnit temporalCyclePeriodicityUnit, String identifier) {
        this.cyclePartPeriodicity = new CyclePartPeriodicity(repetitionValue, periodicityValue,
                new Duration(minCycleDurationValue, maxCycleDurationValue, durationCycleValue, temporalCycleDurationUnit), temporalCyclePeriodicityUnit, new ConditionSet(identifier));
        this.cyclePartDefinition.setCyclePartPeriodicityID(cyclePartPeriodicity);
    }

    public void check() {
        CyclePartDefinition partDefinition = checkCyclePartDefinition(cyclePartDefinition);
        cyclePartDefinition = partDefinition;
    }

    public static CyclePartDefinition checkCyclePartDefinition(CyclePartDefinition cyclePartDefinit) {
        CyclePartDefinition partDefinition = null;

        if (cyclePartDefinit.asDuration()) {
            Duration dur = DurationComposite.checkDuration(cyclePartDefinit.getDurationID());

            cyclePartDefinit.setDurationID(dur);
            partDefinition = getCyclePartDefinitionFacade().findByDurationID(dur);
        }

        if (cyclePartDefinit.asPeriodicity()) {
            CyclePartPeriodicity partPeriodicity = CyclePartPeriodicityComposite.checkCyclePartPeriodicity(
                    cyclePartDefinit.getCyclePartPeriodicityID());

            cyclePartDefinit.setCyclePartPeriodicityID(partPeriodicity);
            partDefinition = getCyclePartDefinitionFacade().findByCyclePartPeriodicityID(
                    partPeriodicity);
        }

        if (partDefinition == null) {
            getCyclePartDefinitionFacade().create(cyclePartDefinit);
            partDefinition = cyclePartDefinit;
        }
        
        return partDefinition;
    }

}
