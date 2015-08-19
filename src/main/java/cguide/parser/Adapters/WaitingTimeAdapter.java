/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Adapters;

import cguide.execution.entities.Action;
import cguide.execution.entities.ClinicalTask;
import cguide.execution.entities.Decision;
import cguide.execution.entities.Plan;
import cguide.execution.entities.Question;
import cguide.execution.entities.WaitingTime;
import cguide.parser.Composite.WaitingTimeComposite;
import cguide.parser.GuidelineHandler;
import java.util.List;

/**
 *
 * @author António
 */
public class WaitingTimeAdapter implements GuidelineInterface {

    private List<com.compguide.Persistence.Entities.WaitingTime> waitingTime;
    private GuidelineHandler guidelineHandler;

    public WaitingTimeAdapter() {
    }

    @Override
    public GuidelineInterface fetchObjectFromOwl(String name) {
        ClinicalTask task = guidelineHandler.getClinicalTask(name);

        if (task.getTaskType().toLowerCase().equals("plan")) {
            WaitingTime wtg = ((Plan) task).getWaitingTime();
            WaitingTimeComposite composite = new WaitingTimeComposite();

            if (wtg.asExactValue()) {
                composite.setWaitingTime(
                        wtg.getExactWaitingTime()
                );
            }

            if (wtg.asInterval()) {
                composite.setWaitingTime(
                        wtg.getMinWaitingTime(),
                        wtg.getMaxWaitingTime()
                );

            }

            composite.setTemporalUnit(wtg.getTemporalUnit());
            composite.check();

            waitingTime.add(composite.getWaitingTime());
        }
        if (task.getTaskType().toLowerCase().equals("question")) {
            WaitingTime wtg = ((Question) task).getWaitingTime();
            WaitingTimeComposite composite = new WaitingTimeComposite();

            if (wtg.asExactValue()) {
                composite.setWaitingTime(
                        wtg.getExactWaitingTime()
                );
            }

            if (wtg.asInterval()) {
                composite.setWaitingTime(
                        wtg.getMinWaitingTime(),
                        wtg.getMaxWaitingTime()
                );

            }

            composite.setTemporalUnit(wtg.getTemporalUnit());
            composite.check();

            waitingTime.add(composite.getWaitingTime());
        }
        if (task.getTaskType().toLowerCase().equals("decision")) {
            WaitingTime wtg = ((Decision) task).getWaitingTime();
            WaitingTimeComposite composite = new WaitingTimeComposite();

            if (wtg.asExactValue()) {
                composite.setWaitingTime(
                        wtg.getExactWaitingTime()
                );
            }

            if (wtg.asInterval()) {
                composite.setWaitingTime(
                        wtg.getMinWaitingTime(),
                        wtg.getMaxWaitingTime()
                );

            }

            composite.setTemporalUnit(wtg.getTemporalUnit());
            composite.check();
        }
        if (task.getTaskType().toLowerCase().equals("action")) {
            WaitingTime wtg = ((Action) task).getWaitingTime();
            WaitingTimeComposite composite = new WaitingTimeComposite();

            if (wtg.asExactValue()) {
                composite.setWaitingTime(
                        wtg.getExactWaitingTime()
                );
            }

            if (wtg.asInterval()) {
                composite.setWaitingTime(
                        wtg.getMinWaitingTime(),
                        wtg.getMaxWaitingTime()
                );

            }

            composite.setTemporalUnit(wtg.getTemporalUnit());
            composite.check();

            waitingTime.add(composite.getWaitingTime());

        }
        return this;
    }

    @Override
    public Object getObject() {
        return waitingTime;
    }

}
