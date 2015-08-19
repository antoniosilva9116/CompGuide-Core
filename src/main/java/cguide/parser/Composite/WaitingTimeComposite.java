/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Composite;

import com.compguide.Persistence.Entities.SessionBeans.WaitingTimeFacade;
import com.compguide.Persistence.Entities.TemporalUnit;
import com.compguide.Persistence.Entities.WaitingTime;

/**
 *
 * @author António
 */
public class WaitingTimeComposite {

    private WaitingTime waitingTime;
    private TemporalUnit temporalUnit;
    private static com.compguide.Persistence.Entities.SessionBeans.WaitingTimeFacade ejbWaitingTimeFacade;

    public static WaitingTimeFacade getWaitingTimeFacade() {
        if (ejbWaitingTimeFacade == null) {
            ejbWaitingTimeFacade = new WaitingTimeFacade();
        }
        return ejbWaitingTimeFacade;
    }

    public void setWaitingTime(Double minWaitingTime, Double maxWaitingTime) {
        this.waitingTime = new WaitingTime(minWaitingTime, maxWaitingTime);
    }

    public void setWaitingTime(Double exactWaitingTime) {
        this.waitingTime = new WaitingTime(exactWaitingTime);
    }

    public void setTemporalUnit(String value) {
        this.temporalUnit = new TemporalUnit(value);
    }

    public void check() {
        WaitingTime time = checkWaitingTime(waitingTime);
        waitingTime = time;
    }

    public WaitingTime getWaitingTime() {
        return waitingTime;
    }

    public static WaitingTime checkWaitingTime(WaitingTime waitingTime) {
        WaitingTime time = null;

        if (waitingTime.asExactValue()) {
            TemporalUnit temporalUnit = TemporalUnitComposite.checkTemporalUnit(waitingTime.getTemporalUnitID());

            waitingTime.setTemporalUnitID(temporalUnit);

            time = getWaitingTimeFacade().findByExactWaitingTimeAndTemporalUnitID(
                    temporalUnit,
                    waitingTime.getExactWaitingTime()
            );
        }
        if (waitingTime.asInterval()) {
            TemporalUnit temporalUnit = TemporalUnitComposite.checkTemporalUnit(waitingTime.getTemporalUnitID());

            waitingTime.setTemporalUnitID(temporalUnit);

            time = getWaitingTimeFacade().findByMinMaxWaitingTimeAndTemporalUnitID(
                    temporalUnit,
                    waitingTime.getMinWaitingTime(),
                    waitingTime.getMaxWaitingTime()
            );
        }

        if (time == null) {
            getWaitingTimeFacade().create(waitingTime);
            time = waitingTime;
        }
        return time;
    }
}
