/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compguide.Persistence.Entities.SessionBeans;

import com.compguide.Persistence.Entities.Duration;
import com.compguide.Persistence.Entities.TemporalUnit;
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
public class DurationFacade extends AbstractFacade<Duration> {

    @PersistenceContext(unitName = "CGuide_CGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DurationFacade() {
        super(Duration.class);
    }

    public Duration findByDurationValueAndTemporalUnit(TemporalUnit temporalUnitID, Double durationValue) {
        Duration duration = null;

        Query query = em.createNamedQuery("Duration.findByDurationValueANDTemporalUnit", Duration.class);
        query.setParameter("temporalUnitID", temporalUnitID);
        query.setParameter("durationValue", durationValue);

        try {
            duration = (Duration) query.getSingleResult();
        } catch (javax.ejb.EJBException | javax.persistence.NoResultException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return duration;
    }

    public Duration findByMinMaxValueAndTemporalUnit(TemporalUnit temporalUnitID, Double minDurationValue, Double maxDurationValue) {
        Duration duration = null;

        Query query = em.createNamedQuery("Duration.findByMinMaxDurationValueAndTemporalUnit", Duration.class);
        query.setParameter("temporalUnitID", temporalUnitID);
        query.setParameter("minDurationValue", minDurationValue);
        query.setParameter("maxDurationValue", minDurationValue);
        
        try {
            duration = (Duration) query.getSingleResult();
        } catch (javax.ejb.EJBException | javax.persistence.NoResultException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return duration;
    }

}