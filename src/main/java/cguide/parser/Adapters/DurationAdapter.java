/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Adapters;

import cguide.execution.entities.Action;
import cguide.execution.entities.ClinicalTask;
import cguide.execution.entities.Duration;
import cguide.execution.entities.Plan;
import cguide.parser.Composite.DurationComposite;
import cguide.parser.GuidelineHandler;
import com.compguide.Persistence.Entities.SessionBeans.TemporalUnitFacade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author António
 */
public class DurationAdapter implements GuidelineInterface {

    private List<com.compguide.Persistence.Entities.Duration> durations = new ArrayList<com.compguide.Persistence.Entities.Duration>();
    private GuidelineHandler guidelineHandler;
    private com.compguide.Persistence.Entities.SessionBeans.TemporalUnitFacade ejbTemporalUnitFacade;

    public DurationAdapter() {
    }

    public TemporalUnitFacade getTemporalUnitFacade() {
        if (ejbTemporalUnitFacade == null) {
            ejbTemporalUnitFacade = new TemporalUnitFacade();
        }
        return ejbTemporalUnitFacade;
    }

    @Override
    public GuidelineInterface fetchObjectFromOwl(String name) {
        ClinicalTask task = guidelineHandler.getClinicalTask(name);

        if (task.getTaskType().toLowerCase().equals("plan")) {
            Duration dur = ((Plan) task).getDuration();

            DurationComposite composite = new DurationComposite();
            composite.setDuration(dur.getMinDurationValue(),
                    dur.getMaxDurationValue(),
                    dur.getDurationValue());
            composite.setTemporalUnit(dur.getTemporalUnit());

            composite.check();

            durations.add(composite.getDuration());
        }

        if (task.getTaskType().toLowerCase().equals("action")) {
            Duration dur = ((Action) task).getDuration();

            DurationComposite composite = new DurationComposite();
            composite.setDuration(dur.getMinDurationValue(),
                    dur.getMaxDurationValue(),
                    dur.getDurationValue());
            composite.setTemporalUnit(dur.getTemporalUnit());

            composite.check();

            durations.add(composite.getDuration());
        }
        return this;
    }

    @Override
    public Object getObject() {
        return durations;
    }

}
