/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Adapters;

import cguide.execution.entities.*;
import cguide.parser.Composite.CyclePartPeriodicityComposite;

import cguide.parser.GuidelineHandler;
import cguide.utils.Utils;
import com.compguide.Persistence.Entities.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author António
 */
public class CyclePartPeriodicityAdapter implements GuidelineInterface {

    private List<com.compguide.Persistence.Entities.CyclePartPeriodicity> cyclePartPeriodicity = new ArrayList<com.compguide.Persistence.Entities.CyclePartPeriodicity>();
    private GuidelineHandler guidelineHandler;

    public CyclePartPeriodicityAdapter() {
    }

    @Override
    public GuidelineInterface fetchObjectFromOwl(String name) {
        ClinicalTask task = guidelineHandler.getClinicalTask(name);

        if (task.getTaskType().toLowerCase().equals("plan")) {
            Periodicity per = ((Plan) task).getPeriodicity();
            if (per.haveCyclePart()) {
                CyclePartDefinition cyclePartDef = ((Plan) task).getPeriodicity().getCyclePartDefinition();

                if (cyclePartDef.asPeriodicity()) {
                    CyclePartPeriodicity cpp = cyclePartDef.getCyclePartPeriodicity();

                    CyclePartPeriodicityComposite cyclePartDefinitionComposite = Utils.checkCyclePartPeriodicity(cpp);

                    cyclePartPeriodicity.add(cyclePartDefinitionComposite.getCyclePartPeriodicity());
                }
            }

        }

        if (task.getTaskType().toLowerCase().equals("action")) {
            Periodicity per = ((Action) task).getPeriodicity();
            if (per.haveCyclePart()) {
                CyclePartDefinition cyclePartDef = ((Action) task).getPeriodicity().getCyclePartDefinition();

                if (cyclePartDef.asPeriodicity()) {
                    CyclePartPeriodicity cpp = cyclePartDef.getCyclePartPeriodicity();

                    CyclePartPeriodicityComposite cyclePartDefinitionComposite = Utils.checkCyclePartPeriodicity(cpp);

                    cyclePartPeriodicity.add(cyclePartDefinitionComposite.getCyclePartPeriodicity());

                }

            }
        }
        return this;
    }

    @Override
    public Object getObject() {
        return cyclePartPeriodicity;
    }

}
