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
import cguide.parser.GuidelineHandler;

/**
 *
 * @author António
 */
public class StopConditionSetAdapter implements GuidelineInterface {

    private com.compguide.Persistence.Entities.StopConditionSet stopConditionSet;
    private GuidelineHandler guidelineHandler;

    public StopConditionSetAdapter() {
    }

    @Override
    public GuidelineInterface fetchObjectFromOwl(String name) {
        ClinicalTask task = guidelineHandler.getClinicalTask(name);

        if (task.getTaskType().toLowerCase().equals("plan")) {
            Periodicity per = ((Plan) task).getPeriodicity();
            
            
        }
        if (task.getTaskType().toLowerCase().equals("question")) {
            WaitingTime wtg = ((Question) task).getWaitingTime();
        }
        if (task.getTaskType().toLowerCase().equals("decision")) {      
            WaitingTime wtg = ((Decision) task).getWaitingTime();
        }
        if (task.getTaskType().toLowerCase().equals("action")) {
            Periodicity per = ((Action) task).getPeriodicity();
            Duration dur = ((Action) task).getDuration();
            WaitingTime wtg = ((Action) task).getWaitingTime();
        }
        return this;
    }

    @Override
    public Object getObject() {
        return stopConditionSet;
    }

}
