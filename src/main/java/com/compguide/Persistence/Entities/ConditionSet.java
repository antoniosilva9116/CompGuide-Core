/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compguide.Persistence.Entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author António
 */
@Entity
@Table(name = "conditionset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConditionSet.findAll", query = "SELECT c FROM ConditionSet c"),
    @NamedQuery(name = "ConditionSet.findByIdconditionset", query = "SELECT c FROM ConditionSet c WHERE c.idconditionset = :idconditionset"),
    @NamedQuery(name = "ConditionSet.findByIdentifier", query = "SELECT c FROM ConditionSet c WHERE c.identifier = :identifier")})
public class ConditionSet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconditionset")
    private Integer idconditionset;
    @Size(max = 45)
    @Column(name = "identifier")
    private String identifier;
    @OneToMany(mappedBy = "conditionSetID")
    private List<CyclePartPeriodicity> cyclePartPeriodicityList;
    @JoinColumn(name = "idtask", referencedColumnName = "idtask")
    @ManyToOne(optional = false)
    private Task idtask;

    public ConditionSet() {
    }

    public ConditionSet(String identifier) {
        this.identifier = identifier;
    }
    
    public ConditionSet(Integer idconditionset) {
        this.idconditionset = idconditionset;
    }

    public Integer getIdconditionset() {
        return idconditionset;
    }

    public void setIdconditionset(Integer idconditionset) {
        this.idconditionset = idconditionset;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @XmlTransient
    @JsonIgnore
    public List<CyclePartPeriodicity> getCyclePartPeriodicityList() {
        return cyclePartPeriodicityList;
    }

    public void setCyclePartPeriodicityList(List<CyclePartPeriodicity> cyclePartPeriodicityList) {
        this.cyclePartPeriodicityList = cyclePartPeriodicityList;
    }

    public Task getIdtask() {
        return idtask;
    }

    public void setIdtask(Task idtask) {
        this.idtask = idtask;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconditionset != null ? idconditionset.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConditionSet)) {
            return false;
        }
        ConditionSet other = (ConditionSet) object;
        if ((this.idconditionset == null && other.idconditionset != null) || (this.idconditionset != null && !this.idconditionset.equals(other.idconditionset))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.compguide.Persistence.Entities.ConditionSet[ idconditionset=" + idconditionset + " ]";
    }
    
}
