/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Adapters;

import cguide.execution.entities.Action;
import cguide.execution.entities.ClinicalTask;
import cguide.execution.entities.*;
import cguide.execution.entities.Duration;
import cguide.execution.entities.Periodicity;
import cguide.execution.entities.Plan;
import cguide.execution.entities.WaitingTime;
import cguide.parser.Composite.CyclePartDefinitionComposite;
import cguide.parser.GuidelineHandler;
import cguide.utils.Utils;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author António
 */
public class CyclePartDefinitionAdapter implements GuidelineInterface {

    private List<com.compguide.Persistence.Entities.CyclePartDefinition> cyclePartDefinition = new ArrayList<com.compguide.Persistence.Entities.CyclePartDefinition>();
    private GuidelineHandler guidelineHandler;

    public CyclePartDefinitionAdapter() {
    }

    @Override
    public GuidelineInterface fetchObjectFromOwl(String name) {
        ClinicalTask task = guidelineHandler.getClinicalTask(name);

        if (task.getTaskType().toLowerCase().equals("plan")) {
            Periodicity per = ((Plan) task).getPeriodicity();
            if (per.haveCyclePart()) {
                CyclePartDefinition cyclePartDef = ((Plan) task).getPeriodicity().getCyclePartDefinition();

                CyclePartDefinitionComposite cyclePartDefinitionComposite = Utils.checkCyclePartDefinition(cyclePartDef);

                cyclePartDefinition.add(cyclePartDefinitionComposite.getCyclePartDefinition());
            }

        }

        if (task.getTaskType().toLowerCase().equals("action")) {
            Periodicity per = ((Action) task).getPeriodicity();
            if (per.haveCyclePart()) {
                CyclePartDefinition cyclePartDef = ((Plan) task).getPeriodicity().getCyclePartDefinition();

                CyclePartDefinitionComposite cyclePartDefinitionComposite = Utils.checkCyclePartDefinition(cyclePartDef);

                cyclePartDefinition.add(cyclePartDefinitionComposite.getCyclePartDefinition());
            }
        }
        return this;
    }

    @Override
    public Object getObject() {
        return cyclePartDefinition;
    }

}
