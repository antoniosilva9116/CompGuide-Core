/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser;

import com.compguide.Persistence.Entities.*;
import com.compguide.Persistence.Entities.SessionBeans.CyclePartDefinitionFacade;
import com.compguide.Persistence.Entities.SessionBeans.CyclePartPeriodicityFacade;
import com.compguide.Persistence.Entities.SessionBeans.DurationFacade;
import com.compguide.Persistence.Entities.SessionBeans.PeriodicityFacade;
import com.compguide.Persistence.Entities.SessionBeans.StopConditionSetFacade;
import com.compguide.Persistence.Entities.SessionBeans.TemporalUnitFacade;
import com.compguide.Persistence.Entities.SessionBeans.WaitingTimeFacade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author António
 */
public class GuidelineComposite {

    List<Duration> durations = new ArrayList<Duration>();
    List<Periodicity> periodicities = new ArrayList<Periodicity>();
    List<WaitingTime> waitingTimes = new ArrayList<WaitingTime>();
    List<CyclePartDefinition> cyclePartDefinitions = new ArrayList<CyclePartDefinition>();
    List<CyclePartPeriodicity> cyclePartPeriodicities = new ArrayList<CyclePartPeriodicity>();
    List<TemporalUnit> temporalUnits = new ArrayList<TemporalUnit>();
    List<StopConditionSet> stopConditionSets = new ArrayList<StopConditionSet>();

    private com.compguide.Persistence.Entities.SessionBeans.CyclePartDefinitionFacade ejbCyclePartDefinitionFacade;
    private com.compguide.Persistence.Entities.SessionBeans.CyclePartPeriodicityFacade ejbCyclePartPeriodicityFacade;
    private com.compguide.Persistence.Entities.SessionBeans.DurationFacade ejbDurationFacade;
    private com.compguide.Persistence.Entities.SessionBeans.PeriodicityFacade ejbPeriodicityFacade;
    private com.compguide.Persistence.Entities.SessionBeans.WaitingTimeFacade ejbWaitingTimeFacade;
    private com.compguide.Persistence.Entities.SessionBeans.StopConditionSetFacade ejbStopConditionSetFacade;
    private com.compguide.Persistence.Entities.SessionBeans.TemporalUnitFacade ejbTemporalUnitFacade;

    public GuidelineComposite() {

    }

    public List<Duration> getDurations() {
        return durations;
    }

    public void setDurations(List<Duration> durations) {
        this.durations = durations;
    }

    public List<Periodicity> getPeriodicities() {
        return periodicities;
    }

    public void setPeriodicities(List<Periodicity> periodicities) {
        this.periodicities = periodicities;
    }

    public List<WaitingTime> getWaitingTimes() {
        return waitingTimes;
    }

    public void setWaitingTimes(List<WaitingTime> waitingTimes) {
        this.waitingTimes = waitingTimes;
    }

    public List<CyclePartDefinition> getCyclePartDefinitions() {
        return cyclePartDefinitions;
    }

    public void setCyclePartDefinitions(List<CyclePartDefinition> cyclePartDefinitions) {
        this.cyclePartDefinitions = cyclePartDefinitions;
    }

    public List<CyclePartPeriodicity> getCyclePartPeriodicities() {
        return cyclePartPeriodicities;
    }

    public void setCyclePartPeriodicities(List<CyclePartPeriodicity> cyclePartPeriodicities) {
        this.cyclePartPeriodicities = cyclePartPeriodicities;
    }

    public List<TemporalUnit> getTemporalUnits() {
        return temporalUnits;
    }

    public void setTemporalUnits(List<TemporalUnit> temporalUnits) {
        this.temporalUnits = temporalUnits;
    }

    public List<StopConditionSet> getStopConditionSets() {
        return stopConditionSets;
    }

    public void setStopConditionSets(List<StopConditionSet> stopConditionSets) {
        this.stopConditionSets = stopConditionSets;
    }

    public WaitingTimeFacade getWaitingTimeFacade() {
        if (ejbWaitingTimeFacade == null) {
            ejbWaitingTimeFacade = new WaitingTimeFacade();
        }
        return ejbWaitingTimeFacade;
    }

    public CyclePartDefinitionFacade getCyclePartDefinitionFacade() {
        if (ejbCyclePartDefinitionFacade == null) {
            ejbCyclePartDefinitionFacade = new CyclePartDefinitionFacade();
        }
        return ejbCyclePartDefinitionFacade;
    }

    public CyclePartPeriodicityFacade getCyclePartPeriodicityFacade() {
        if (ejbCyclePartPeriodicityFacade == null) {
            ejbCyclePartPeriodicityFacade = new CyclePartPeriodicityFacade();
        }
        return ejbCyclePartPeriodicityFacade;
    }

    public DurationFacade getDurationFacade() {
        if (ejbDurationFacade == null) {
            ejbDurationFacade = new DurationFacade();
        }
        return ejbDurationFacade;
    }

    public PeriodicityFacade getPeriodicityFacade() {
        if (ejbPeriodicityFacade == null) {
            ejbPeriodicityFacade = new PeriodicityFacade();
        }
        return ejbPeriodicityFacade;
    }

    public StopConditionSetFacade getStopConditionSetFacade() {
        if (ejbStopConditionSetFacade == null) {
            ejbStopConditionSetFacade = new StopConditionSetFacade();
        }
        return ejbStopConditionSetFacade;
    }

    public TemporalUnitFacade getTemporalUnitFacade() {
        if (ejbTemporalUnitFacade == null) {
            ejbTemporalUnitFacade = new TemporalUnitFacade();
        }
        return ejbTemporalUnitFacade;
    }

    public void addDuration(Duration duration) {
        if (!durations.contains(duration)) {
            durations.add(duration);
        }
    }

    public void removeDuration(Duration duration) {
        if (durations.contains(duration)) {
            durations.remove(duration);
        }
    }

    public void addPeriodicity(Periodicity periodicity) {
        if (!periodicities.contains(periodicity)) {
            periodicities.add(periodicity);
        }
    }

    public void removePeriodicity(Periodicity periodicity) {
        if (!periodicities.contains(periodicity)) {
            periodicities.remove(periodicity);
        }
    }

    public void addWaitingTime(WaitingTime waitingTime) {
        if (!waitingTimes.contains(waitingTime)) {
            waitingTimes.add(waitingTime);
        }
    }

    public void removeWaitingTime(WaitingTime waitingTime) {
        if (!waitingTimes.contains(waitingTime)) {
            waitingTimes.remove(waitingTime);
        }
    }

    public void addCyclePartDefinition(CyclePartDefinition cyclePartDefinition) {
        if (!cyclePartDefinitions.contains(cyclePartDefinition)) {
            cyclePartDefinitions.add(cyclePartDefinition);
        }
    }

    public void removeCyclePartDefinition(CyclePartDefinition cyclePartDefinition) {
        if (!cyclePartDefinitions.contains(cyclePartDefinition)) {
            cyclePartDefinitions.remove(cyclePartDefinition);
        }
    }

    public void addCyclePartPeriodicity(CyclePartPeriodicity cyclePartPeriodicity) {
        if (!cyclePartPeriodicities.contains(cyclePartPeriodicity)) {
            cyclePartPeriodicities.add(cyclePartPeriodicity);
        }
    }

    public void removeCyclePartPeriodicity(CyclePartPeriodicity cyclePartPeriodicity) {
        if (!cyclePartPeriodicities.contains(cyclePartPeriodicity)) {
            cyclePartPeriodicities.remove(cyclePartPeriodicity);
        }
    }

    public void addTemporalUnit(TemporalUnit temporalUnit) {
        if (!temporalUnits.contains(temporalUnit)) {
            temporalUnits.add(temporalUnit);
        }
    }

    public void removeTemporalUnit(TemporalUnit temporalUnit) {
        if (!temporalUnits.contains(temporalUnit)) {
            temporalUnits.remove(temporalUnit);
        }
    }

    public void addStopConditionSet(StopConditionSet stopConditionSet) {
        if (!stopConditionSets.contains(stopConditionSet)) {
            stopConditionSets.add(stopConditionSet);
        }
    }

    public void removeStopConditionSet(StopConditionSet stopConditionSet) {
        if (!stopConditionSets.contains(stopConditionSet)) {
            stopConditionSets.remove(stopConditionSet);
        }
    }

    public void storeAll() {
        if (durations.size() > 0) {
            for (Duration duration : durations) {
                getDurationFacade().create(duration);
            }
        }

        if (periodicities.size() > 0) {
            for (Periodicity periodicity : periodicities) {
                getPeriodicityFacade().create(periodicity);
            }
        }

        if (waitingTimes.size() > 0) {
            for (WaitingTime waitingTime : waitingTimes) {
                getWaitingTimeFacade().create(waitingTime);
            }
        }

        if (cyclePartDefinitions.size() > 0) {
            for (CyclePartDefinition cyclePartDefinition : cyclePartDefinitions) {
                getCyclePartDefinitionFacade().create(cyclePartDefinition);
            }
        }

        if (cyclePartPeriodicities.size() > 0) {
            for (CyclePartPeriodicity cyclePartPeriodicity : cyclePartPeriodicities) {
                getCyclePartPeriodicityFacade().create(cyclePartPeriodicity);
            }
        }

        if (stopConditionSets.size() > 0) {
            for (StopConditionSet stopConditionSet : stopConditionSets) {
                getStopConditionSetFacade().create(stopConditionSet);
            }
        }

        if (temporalUnits.size() > 0) {
            for (TemporalUnit temporalUnit : temporalUnits) {
                getTemporalUnitFacade().create(temporalUnit);
            }
        }
    }

}
