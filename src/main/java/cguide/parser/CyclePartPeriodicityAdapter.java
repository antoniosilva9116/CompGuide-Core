/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cguide.parser;

/**
 *
 * @author António
 */
public class CyclePartPeriodicityAdapter implements GuidelineInterface {

    private com.compguide.Persistence.Entities.CyclePartPeriodicity cyclePartPeriodicity;

    @Override
    public GuidelineInterface getObjectFromOwl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getObject() {
        return cyclePartPeriodicity;
    }

}
