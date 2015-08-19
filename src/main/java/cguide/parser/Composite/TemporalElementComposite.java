/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Composite;

import com.compguide.Persistence.Entities.CyclePartDefinition;
import com.compguide.Persistence.Entities.Periodicity;
import com.compguide.Persistence.Entities.WaitingTime;
import com.compguide.Persistence.Entities.Duration;
import com.compguide.Persistence.Entities.SessionBeans.TemporalElementFacade;
import com.compguide.Persistence.Entities.TemporalElement;
import com.compguide.Persistence.Entities.TemporalUnit;

/**
 *
 * @author António
 */
public class TemporalElementComposite {

    private TemporalElement temporalElement;
    private WaitingTime waitingTime;
    private Duration duration;
    private Periodicity periodicity;
    private static com.compguide.Persistence.Entities.SessionBeans.TemporalElementFacade ejbTemporalElementFacade;

    public TemporalElementComposite() {
    }

    public TemporalElement getTemporalElement() {
        return temporalElement;
    }

    public void setPeriodicity(Integer repetitionValue, double periodicityValue, CyclePartDefinition cyclePartDefinitionID, TemporalUnit temporalUnitID, Duration durationID) {
        this.periodicity = new Periodicity(repetitionValue, periodicityValue, cyclePartDefinitionID, temporalUnitID, durationID);
    }

    public void setDuration(Double minDurationValue, Double maxDurationValue,
            Double durationValue, TemporalUnit temporalDurationUnit) {
        this.duration = new Duration(minDurationValue, maxDurationValue, durationValue, temporalDurationUnit);
    }

    public void setTemporalElement(TemporalElement temporalElement) {
        this.temporalElement = temporalElement;
    }

    public void setWaitingTime(WaitingTime waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    private static TemporalElementFacade getTemporalElementFacade() {
        if (ejbTemporalElementFacade == null) {
            ejbTemporalElementFacade = new TemporalElementFacade();
        }
        return ejbTemporalElementFacade;
    }

    public static TemporalElement checkTemporalElement(TemporalElement temporalElement) {
        TemporalElement element = null;

        if (temporalElement.asDuration()) {
            Duration dur = DurationComposite.checkDuration(temporalElement.getDurationID());

            if (temporalElement.haveWaitingTime()) {
                WaitingTime waitingTime = WaitingTimeComposite.checkWaitingTime(
                        temporalElement.getWaitingTimeID()
                );

                temporalElement.setWaitingTimeID(waitingTime);
                temporalElement.setDurationID(dur);

                element = getTemporalElementFacade().findByDurationIDAndWaitingTimeID(
                        dur,
                        waitingTime
                );
            } else {
                temporalElement.setDurationID(dur);

                element = getTemporalElementFacade().findByDurationID(dur);
            }
        }

        if (temporalElement.asPeriodicity()) {
            Periodicity per = PeriodicityComposite.checkPeriodicity(temporalElement.getPeriodicityID());

            if (temporalElement.haveWaitingTime()) {
                WaitingTime waitingTime = WaitingTimeComposite.checkWaitingTime(
                        temporalElement.getWaitingTimeID()
                );

                temporalElement.setWaitingTimeID(waitingTime);
                temporalElement.setPeriodicityID(per);

                element = getTemporalElementFacade().findByPeriodicityIDAndWaitingTimeID(
                        per,
                        waitingTime
                );
            } else {
                temporalElement.setPeriodicityID(per);

                element = getTemporalElementFacade().findByPeriodicityID(per);
            }
        }

        return element;
    }

}
