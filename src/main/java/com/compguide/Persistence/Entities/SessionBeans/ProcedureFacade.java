/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compguide.Persistence.Entities.SessionBeans;

import com.compguide.Persistence.Entities.Procedure;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author António
 */
@Stateless
public class ProcedureFacade extends AbstractFacade<Procedure> {
    @PersistenceContext(unitName = "CGuide_CGuide_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcedureFacade() {
        super(Procedure.class);
    }
    
}