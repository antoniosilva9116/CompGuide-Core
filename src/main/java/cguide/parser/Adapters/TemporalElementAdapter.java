/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Adapters;

import cguide.execution.entities.Action;
import cguide.execution.entities.ClinicalTask;
import cguide.execution.entities.Decision;
import cguide.execution.entities.Duration;
import cguide.execution.entities.Periodicity;
import cguide.execution.entities.Plan;
import cguide.execution.entities.Question;
import cguide.execution.entities.WaitingTime;
import cguide.parser.Composite.DurationComposite;
import cguide.parser.Composite.PeriodicityComposite;
import cguide.parser.Composite.TemporalElementComposite;
import cguide.parser.Composite.WaitingTimeComposite;
import cguide.parser.GuidelineHandler;
import cguide.utils.Utils;
import com.compguide.Persistence.Entities.TemporalElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author António
 */
public class TemporalElementAdapter implements GuidelineInterface {

    private List<com.compguide.Persistence.Entities.TemporalElement> temporalElements = new ArrayList<TemporalElement>();
    private GuidelineHandler guidelineHandler;

    public TemporalElementAdapter() {
    }

    @Override
    public GuidelineInterface fetchObjectFromOwl(String name) {
        ClinicalTask task = guidelineHandler.getClinicalTask(name);

        if (task.getTaskType().toLowerCase().equals("plan")) {
            TemporalElementComposite temporalElementComposite = new TemporalElementComposite();

            if (((Plan) task).asDuration()) {
                Duration dur = ((Plan) task).getDuration();

                DurationComposite durationComposite = Utils.checkDuration(dur);
                temporalElementComposite.setDuration(durationComposite.getDuration());
            }

            if (((Plan) task).asPeriodicity()) {
                Periodicity per = ((Plan) task).getPeriodicity();

                PeriodicityComposite periodicityComposite = Utils.checkPeriodicity(per);
                temporalElementComposite.setPeriodicity(periodicityComposite.getPeriodicity());
            }

            if (((Plan) task).haveWaitingTime()) {
                WaitingTime wtg = ((Plan) task).getWaitingTime();

                WaitingTimeComposite waitingTimeComposite = new WaitingTimeComposite();

                waitingTimeComposite.setTemporalUnit(wtg.getTemporalUnit());
                if (wtg.asExactValue()) {
                    waitingTimeComposite.setWaitingTime(wtg.getExactWaitingTime());
                    temporalElementComposite.setWaitingTime(waitingTimeComposite.getWaitingTime());
                }

                if (wtg.asInterval()) {
                    waitingTimeComposite.setWaitingTime(wtg.getMinWaitingTime(), wtg.getMaxWaitingTime());
                    temporalElementComposite.setWaitingTime(waitingTimeComposite.getWaitingTime());
                }
            }

            temporalElements.add(temporalElementComposite.getTemporalElement());
        }
        if (task.getTaskType().toLowerCase().equals("question")) {
            WaitingTime wtg = ((Question) task).getWaitingTime();
            TemporalElementComposite temporalElementComposite = new TemporalElementComposite();
            WaitingTimeComposite waitingTimeComposite = new WaitingTimeComposite();

            waitingTimeComposite.setTemporalUnit(wtg.getTemporalUnit());
            if (wtg.asExactValue()) {
                waitingTimeComposite.setWaitingTime(wtg.getExactWaitingTime());
                temporalElementComposite.setWaitingTime(waitingTimeComposite.getWaitingTime());
            }

            if (wtg.asInterval()) {
                waitingTimeComposite.setWaitingTime(wtg.getMinWaitingTime(), wtg.getMaxWaitingTime());
                temporalElementComposite.setWaitingTime(waitingTimeComposite.getWaitingTime());
            }

            temporalElements.add(temporalElementComposite.getTemporalElement());
        }

        if (task.getTaskType().toLowerCase().equals("decision")) {
            WaitingTime wtg = ((Decision) task).getWaitingTime();
            TemporalElementComposite temporalElementComposite = new TemporalElementComposite();
            WaitingTimeComposite waitingTimeComposite = new WaitingTimeComposite();

            waitingTimeComposite.setTemporalUnit(wtg.getTemporalUnit());
            if (wtg.asExactValue()) {
                waitingTimeComposite.setWaitingTime(wtg.getExactWaitingTime());
                temporalElementComposite.setWaitingTime(waitingTimeComposite.getWaitingTime());
            }

            if (wtg.asInterval()) {
                waitingTimeComposite.setWaitingTime(wtg.getMinWaitingTime(), wtg.getMaxWaitingTime());
                temporalElementComposite.setWaitingTime(waitingTimeComposite.getWaitingTime());
            }

            temporalElements.add(temporalElementComposite.getTemporalElement());
        }
        if (task.getTaskType().toLowerCase().equals("action")) {
            TemporalElementComposite temporalElementComposite = new TemporalElementComposite();

            if (((Action) task).asDuration()) {
                Duration dur = ((Action) task).getDuration();

                DurationComposite durationComposite = Utils.checkDuration(dur);
                temporalElementComposite.setDuration(durationComposite.getDuration());
            }

            if (((Action) task).asPeriodicity()) {
                Periodicity per = ((Action) task).getPeriodicity();

                PeriodicityComposite periodicityComposite = Utils.checkPeriodicity(per);
                temporalElementComposite.setPeriodicity(periodicityComposite.getPeriodicity());
            }

            if (((Action) task).haveWaitingTime()) {
                WaitingTime wtg = ((Action) task).getWaitingTime();

                WaitingTimeComposite waitingTimeComposite = new WaitingTimeComposite();

                waitingTimeComposite.setTemporalUnit(wtg.getTemporalUnit());
                if (wtg.asExactValue()) {
                    waitingTimeComposite.setWaitingTime(wtg.getExactWaitingTime());
                    temporalElementComposite.setWaitingTime(waitingTimeComposite.getWaitingTime());
                }

                if (wtg.asInterval()) {
                    waitingTimeComposite.setWaitingTime(wtg.getMinWaitingTime(), wtg.getMaxWaitingTime());
                    temporalElementComposite.setWaitingTime(waitingTimeComposite.getWaitingTime());
                }
            }

            temporalElements.add(temporalElementComposite.getTemporalElement());
        }

        return this;
    }

    @Override
    public Object getObject() {
        return temporalElements;
    }

  

}
