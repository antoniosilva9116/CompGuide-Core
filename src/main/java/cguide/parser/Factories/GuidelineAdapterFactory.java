/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Factories;

import cguide.parser.Adapters.CyclePartDefinitionAdapter;
import cguide.parser.Adapters.CyclePartPeriodicityAdapter;
import cguide.parser.Adapters.DurationAdapter;
import cguide.parser.Adapters.GuidelineInterface;
import cguide.parser.Adapters.PeriodicityAdapter;
import cguide.parser.Adapters.StopConditionSetAdapter;
import cguide.parser.Adapters.TemporalElementAdapter;
import cguide.parser.Adapters.TemporalUnitAdapter;
import cguide.parser.Adapters.WaitingTimeAdapter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author António
 */
public class GuidelineAdapterFactory {

    private static GuidelineAdapterFactory instance;

    private static final Map<String, GuidelineInterface> services
            = new HashMap<>();

    static {
        services.put("Duration",
                new DurationAdapter()
        );
        services.put("Periodicity",
                new PeriodicityAdapter()
        );
        services.put("CyclePartDefinition",
                new CyclePartDefinitionAdapter()
        );
        services.put("CyclePartPeriodicity",
                new CyclePartPeriodicityAdapter()
        );
        services.put("TemporalElement",
                new TemporalElementAdapter()
        );
        services.put("TemporalUnit",
                new TemporalUnitAdapter()
        );
        services.put("StopConditionSet",
                new StopConditionSetAdapter()
        );
        services.put("WaitingTime",
                new WaitingTimeAdapter()
        );
    }

    public GuidelineAdapterFactory() {
    }

    public synchronized static GuidelineAdapterFactory instance() {
        if (instance == null) {
            instance = new GuidelineAdapterFactory();
        }
        return instance;
    }

    public GuidelineInterface getAdapter(String type) {
        return services.get(type);
    }

    public Object getObjectOwlAdapter(String name, String type) {
        return services.get(type).fetchObjectFromOwl(name).getObject();
    }
}
