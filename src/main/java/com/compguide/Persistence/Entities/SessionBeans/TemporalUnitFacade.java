/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compguide.Persistence.Entities.SessionBeans;

import com.compguide.Persistence.Entities.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author António
 */
@Stateless
public class TemporalUnitFacade extends AbstractFacade<TemporalUnit> {

    @PersistenceContext(unitName = "CGuide_CGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TemporalUnitFacade() {
        super(TemporalUnit.class);
    }

    public TemporalUnit findByValue(String value) {
        TemporalUnit temporalUnit = null;
        try {
            Query query = em.createNamedQuery("TemporalUnit.findByValue", TemporalUnit.class);
            query.setParameter("value", value);

            temporalUnit = (TemporalUnit) query.getSingleResult();
        } catch (javax.ejb.EJBException | javax.persistence.NoResultException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

        return temporalUnit;
    }

}
