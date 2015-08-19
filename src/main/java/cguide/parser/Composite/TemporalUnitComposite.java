/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser.Composite;

import com.compguide.Persistence.Entities.SessionBeans.TemporalUnitFacade;
import com.compguide.Persistence.Entities.TemporalUnit;

/**
 *
 * @author António
 */
public class TemporalUnitComposite {

    private static com.compguide.Persistence.Entities.SessionBeans.TemporalUnitFacade ejbTemporalUnitFacade;

    private static TemporalUnitFacade getTemporalUnitFacade() {
        if (ejbTemporalUnitFacade == null) {
            ejbTemporalUnitFacade = new TemporalUnitFacade();
        }
        return ejbTemporalUnitFacade;
    }

    public static TemporalUnit checkTemporalUnit(TemporalUnit temporalUnit) {

        TemporalUnit temporalUnitID = getTemporalUnitFacade().findByValue(
                temporalUnit.getValue()
        );

        if (temporalUnitID == null) {
            getTemporalUnitFacade().create(temporalUnit);
            temporalUnitID = temporalUnit;
        } else {

        }

        return temporalUnitID;
    }
}
