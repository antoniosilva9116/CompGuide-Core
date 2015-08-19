/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Adapters;

import cguide.execution.entities.*;
import cguide.parser.Composite.CyclePartDefinitionComposite;
import cguide.parser.Composite.CyclePartPeriodicityComposite;
import cguide.parser.Composite.DurationComposite;
import cguide.parser.Composite.PeriodicityComposite;
import cguide.parser.Composite.WaitingTimeComposite;
import cguide.parser.GuidelineHandler;
import cguide.utils.Utils;
import com.compguide.Persistence.Entities.TemporalUnit;
import java.util.List;

/**
 *
 * @author António
 */
public class PeriodicityAdapter implements GuidelineInterface {

    private List<com.compguide.Persistence.Entities.Periodicity> periodicities;
    private GuidelineHandler guidelineHandler;

    public PeriodicityAdapter() {
    }

    @Override
    public GuidelineInterface fetchObjectFromOwl(String name) {
        guidelineHandler.loadGuideline();
        ClinicalTask task = guidelineHandler.getClinicalTask(name);

        if (task.getTaskType().toLowerCase().equals("plan")) {
            Periodicity per = ((Plan) task).getPeriodicity();

            PeriodicityComposite periodicityComposite = Utils.checkPeriodicity(per);

            periodicities.add(periodicityComposite.getPeriodicity());

        }

        if (task.getTaskType().toLowerCase().equals("action")) {
            Periodicity per = ((Action) task).getPeriodicity();

            PeriodicityComposite periodicityComposite = Utils.checkPeriodicity(per);

            periodicities.add(periodicityComposite.getPeriodicity());

        }

        return this;
    }

    @Override
    public Object getObject() {
        return periodicities;
    }

}
