/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Composite;

import cguide.execution.entities.Action;
import cguide.execution.entities.ClinicalTask;
import cguide.execution.entities.Decision;
import cguide.execution.entities.Plan;
import cguide.execution.entities.Question;
import com.compguide.Persistence.Entities.SessionBeans.TaskFacade;
import com.compguide.Persistence.Entities.SessionBeans.TemporalElementFacade;
import com.compguide.Persistence.Entities.Task;
import com.compguide.Persistence.Entities.TemporalElement;

/**
 *
 * @author António
 */
public class TaskComposite {

    private TemporalElement temporalElement;
    private ClinicalTask clinicalTask;
    private com.compguide.Persistence.Entities.SessionBeans.TemporalElementFacade ejbTemporalElementFacade;
    private com.compguide.Persistence.Entities.SessionBeans.TaskFacade ejbTaskFacade;

    public TaskComposite() {
    }

    public TemporalElementFacade getTemporalElementFacade() {
        if (ejbTemporalElementFacade == null) {
            ejbTemporalElementFacade = new TemporalElementFacade();
        }
        return ejbTemporalElementFacade;
    }

    public TaskFacade getTaskFacade() {
        if (ejbTaskFacade == null) {
            ejbTaskFacade = new TaskFacade();
        }
        return ejbTaskFacade;
    }

    public void setTemporalElement(TemporalElement temporalElement) {
        this.temporalElement = temporalElement;
    }

    public void create() {
        if (temporalElement.asDuration()) {
            TemporalElement temporal = getTemporalElementFacade().findByDurationID(temporalElement.getDurationID());

            if (temporal == null) {
                getTemporalElementFacade().create(temporal);
                temporalElement = temporal;
            }

            Task task = new Task();
            

            if (clinicalTask.getTaskType().toLowerCase().equals("action")) {
                Action action = ((Action) clinicalTask);
              
            }
            if (clinicalTask.getTaskType().toLowerCase().equals("decison")) {
                Decision deciosion = ((Decision) clinicalTask);

            }
            if (clinicalTask.getTaskType().toLowerCase().equals("plan")) {
                Plan plan = ((Plan) clinicalTask);
            }
            if (clinicalTask.getTaskType().toLowerCase().equals("question")) {
                Question question = ((Question) clinicalTask);
            }

            getTaskFacade().create(task);

        }

        if (temporalElement.asPeriodicity()) {

        }
    }
}
