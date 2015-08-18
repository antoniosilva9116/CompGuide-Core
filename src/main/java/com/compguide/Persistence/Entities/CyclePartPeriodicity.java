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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author António
 */
@Entity
@Table(name = "cyclepartperiodicity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CyclePartPeriodicity.findAll", query = "SELECT c FROM CyclePartPeriodicity c"),
    @NamedQuery(name = "CyclePartPeriodicity.findByCyclePartPeriodicityID", query = "SELECT c FROM CyclePartPeriodicity c WHERE c.cyclePartPeriodicityID = :cyclePartPeriodicityID"),
    @NamedQuery(name = "CyclePartPeriodicity.findByRepetitionValue", query = "SELECT c FROM CyclePartPeriodicity c WHERE c.repetitionValue = :repetitionValue"),
    @NamedQuery(name = "CyclePartPeriodicity.findByTaskID", query = "SELECT c FROM CyclePartPeriodicity c WHERE c.taskID = :taskID"),
    @NamedQuery(name = "CyclePartPeriodicity.findByPeriodicityValue", query = "SELECT c FROM CyclePartPeriodicity c WHERE c.periodicityValue = :periodicityValue")})
public class CyclePartPeriodicity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CyclePartPeriodicityID")
    private Integer cyclePartPeriodicityID;
    @Column(name = "RepetitionValue")
    private Integer repetitionValue;
    @Column(name = "TaskID")
    private Integer taskID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PeriodicityValue")
    private double periodicityValue;
    @JoinColumn(name = "DurationID", referencedColumnName = "DurationID")
    @ManyToOne
    private Duration durationID;
    @JoinColumn(name = "TemporalUnitID", referencedColumnName = "TemporalUnitID")
    @ManyToOne(optional = false)
    private TemporalUnit temporalUnitID;
    @JoinColumn(name = "ConditionSetID", referencedColumnName = "idconditionset")
    @ManyToOne
    private ConditionSet conditionSetID;
    @OneToMany(mappedBy = "cyclePartPeriodicityID")
    private List<CyclePartDefinition> cyclePartDefinitionList;

    public CyclePartPeriodicity() {
    }

    public CyclePartPeriodicity(Integer cyclePartPeriodicityID) {
        this.cyclePartPeriodicityID = cyclePartPeriodicityID;
    }

    public CyclePartPeriodicity(Integer cyclePartPeriodicityID, double periodicityValue) {
        this.cyclePartPeriodicityID = cyclePartPeriodicityID;
        this.periodicityValue = periodicityValue;
    }

    public Integer getCyclePartPeriodicityID() {
        return cyclePartPeriodicityID;
    }

    public void setCyclePartPeriodicityID(Integer cyclePartPeriodicityID) {
        this.cyclePartPeriodicityID = cyclePartPeriodicityID;
    }

    public Integer getRepetitionValue() {
        return repetitionValue;
    }

    public void setRepetitionValue(Integer repetitionValue) {
        this.repetitionValue = repetitionValue;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public double getPeriodicityValue() {
        return periodicityValue;
    }

    public void setPeriodicityValue(double periodicityValue) {
        this.periodicityValue = periodicityValue;
    }

    public Duration getDurationID() {
        return durationID;
    }

    public void setDurationID(Duration durationID) {
        this.durationID = durationID;
    }

    public TemporalUnit getTemporalUnitID() {
        return temporalUnitID;
    }

    public void setTemporalUnitID(TemporalUnit temporalUnitID) {
        this.temporalUnitID = temporalUnitID;
    }

    public ConditionSet getConditionSetID() {
        return conditionSetID;
    }

    public void setConditionSetID(ConditionSet conditionSetID) {
        this.conditionSetID = conditionSetID;
    }

    @XmlTransient
    @JsonIgnore
    public List<CyclePartDefinition> getCyclePartDefinitionList() {
        return cyclePartDefinitionList;
    }

    public void setCyclePartDefinitionList(List<CyclePartDefinition> cyclePartDefinitionList) {
        this.cyclePartDefinitionList = cyclePartDefinitionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cyclePartPeriodicityID != null ? cyclePartPeriodicityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CyclePartPeriodicity)) {
            return false;
        }
        CyclePartPeriodicity other = (CyclePartPeriodicity) object;
        if ((this.cyclePartPeriodicityID == null && other.cyclePartPeriodicityID != null) || (this.cyclePartPeriodicityID != null && !this.cyclePartPeriodicityID.equals(other.cyclePartPeriodicityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.compguide.Persistence.Entities.CyclePartPeriodicity[ cyclePartPeriodicityID=" + cyclePartPeriodicityID + " ]";
    }
    
}
