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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author António
 */
@Entity
@Table(name = "duration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Duration.findAll", query = "SELECT d FROM Duration d"),
    @NamedQuery(name = "Duration.findByDurationID", query = "SELECT d FROM Duration d WHERE d.durationID = :durationID"),
    @NamedQuery(name = "Duration.findByMinDurationValue", query = "SELECT d FROM Duration d WHERE d.minDurationValue = :minDurationValue"),
    @NamedQuery(name = "Duration.findByMaxDurationValue", query = "SELECT d FROM Duration d WHERE d.maxDurationValue = :maxDurationValue"),
    @NamedQuery(name = "Duration.findByDurationValue", query = "SELECT d FROM Duration d WHERE d.durationValue = :durationValue")})
public class Duration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DurationID")
    private Integer durationID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MinDurationValue")
    private Double minDurationValue;
    @Column(name = "MaxDurationValue")
    private Double maxDurationValue;
    @Column(name = "DurationValue")
    private Integer durationValue;
    @OneToMany(mappedBy = "durationID")
    private List<TemporalElement> temporalElementList;
    @JoinColumn(name = "TemporalUnitID", referencedColumnName = "TemporalUnitID")
    @ManyToOne(optional = false)
    private TemporalUnit temporalUnitID;
    @OneToMany(mappedBy = "durationID")
    private List<CyclePartPeriodicity> cyclePartPeriodicityList;
    @OneToMany(mappedBy = "durationID")
    private List<Periodicity> periodicityList;
    @OneToMany(mappedBy = "durationID")
    private List<CyclePartDefinition> cyclePartDefinitionList;

    public Duration() {
    }

    public Duration(Integer durationID) {
        this.durationID = durationID;
    }

    public Integer getDurationID() {
        return durationID;
    }

    public void setDurationID(Integer durationID) {
        this.durationID = durationID;
    }

    public Double getMinDurationValue() {
        return minDurationValue;
    }

    public void setMinDurationValue(Double minDurationValue) {
        this.minDurationValue = minDurationValue;
    }

    public Double getMaxDurationValue() {
        return maxDurationValue;
    }

    public void setMaxDurationValue(Double maxDurationValue) {
        this.maxDurationValue = maxDurationValue;
    }

    public Integer getDurationValue() {
        return durationValue;
    }

    public void setDurationValue(Integer durationValue) {
        this.durationValue = durationValue;
    }

    @XmlTransient
    @JsonIgnore
    public List<TemporalElement> getTemporalElementList() {
        return temporalElementList;
    }

    public void setTemporalElementList(List<TemporalElement> temporalElementList) {
        this.temporalElementList = temporalElementList;
    }

    public TemporalUnit getTemporalUnitID() {
        return temporalUnitID;
    }

    public void setTemporalUnitID(TemporalUnit temporalUnitID) {
        this.temporalUnitID = temporalUnitID;
    }

    @XmlTransient
    @JsonIgnore
    public List<CyclePartPeriodicity> getCyclePartPeriodicityList() {
        return cyclePartPeriodicityList;
    }

    public void setCyclePartPeriodicityList(List<CyclePartPeriodicity> cyclePartPeriodicityList) {
        this.cyclePartPeriodicityList = cyclePartPeriodicityList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Periodicity> getPeriodicityList() {
        return periodicityList;
    }

    public void setPeriodicityList(List<Periodicity> periodicityList) {
        this.periodicityList = periodicityList;
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
        hash += (durationID != null ? durationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Duration)) {
            return false;
        }
        Duration other = (Duration) object;
        if ((this.durationID == null && other.durationID != null) || (this.durationID != null && !this.durationID.equals(other.durationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.compguide.Persistence.Entities.Duration[ durationID=" + durationID + " ]";
    }
    
}
