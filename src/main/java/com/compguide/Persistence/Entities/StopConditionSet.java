/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compguide.Persistence.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author António
 */
@Entity
@Table(name = "stopconditionset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StopConditionSet.findAll", query = "SELECT s FROM StopConditionSet s"),
    @NamedQuery(name = "StopConditionSet.findByStopConditionSetID", query = "SELECT s FROM StopConditionSet s WHERE s.stopConditionSetID = :stopConditionSetID"),
    @NamedQuery(name = "StopConditionSet.findByIdentifier", query = "SELECT s FROM StopConditionSet s WHERE s.identifier = :identifier")})
public class StopConditionSet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "StopConditionSetID")
    private Integer stopConditionSetID;
    @Size(max = 255)
    @Column(name = "Identifier")
    private String identifier;
    @JoinColumn(name = "PeriodicityID", referencedColumnName = "PeriodicityID")
    @ManyToOne(optional = false)
    private Periodicity periodicityID;

    public StopConditionSet() {
    }

    public StopConditionSet(Integer stopConditionSetID) {
        this.stopConditionSetID = stopConditionSetID;
    }

    public Integer getStopConditionSetID() {
        return stopConditionSetID;
    }

    public void setStopConditionSetID(Integer stopConditionSetID) {
        this.stopConditionSetID = stopConditionSetID;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Periodicity getPeriodicityID() {
        return periodicityID;
    }

    public void setPeriodicityID(Periodicity periodicityID) {
        this.periodicityID = periodicityID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stopConditionSetID != null ? stopConditionSetID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StopConditionSet)) {
            return false;
        }
        StopConditionSet other = (StopConditionSet) object;
        if ((this.stopConditionSetID == null && other.stopConditionSetID != null) || (this.stopConditionSetID != null && !this.stopConditionSetID.equals(other.stopConditionSetID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.compguide.Persistence.Entities.StopConditionSet[ stopConditionSetID=" + stopConditionSetID + " ]";
    }
    
}
