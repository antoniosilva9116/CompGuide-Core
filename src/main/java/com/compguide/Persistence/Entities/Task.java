/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compguide.Persistence.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author António
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findByIdtask", query = "SELECT t FROM Task t WHERE t.idtask = :idtask"),
    @NamedQuery(name = "Task.findByTime", query = "SELECT t FROM Task t WHERE t.time = :time"),
    @NamedQuery(name = "Task.findByTaskType", query = "SELECT t FROM Task t WHERE t.taskType = :taskType"),
    @NamedQuery(name = "Task.findByTaskFormat", query = "SELECT t FROM Task t WHERE t.taskFormat = :taskFormat"),
    @NamedQuery(name = "Task.findByTaskIdentifier", query = "SELECT t FROM Task t WHERE t.taskIdentifier = :taskIdentifier"),
    @NamedQuery(name = "Task.findByTaskPlan", query = "SELECT t FROM Task t WHERE t.taskPlan = :taskPlan"),
    @NamedQuery(name = "Task.findByTaskcol", query = "SELECT t FROM Task t WHERE t.taskcol = :taskcol"),
    @NamedQuery(name = "Task.findByRepetitionValue", query = "SELECT t FROM Task t WHERE t.repetitionValue = :repetitionValue")})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtask")
    private Integer idtask;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Size(max = 45)
    @Column(name = "taskType")
    private String taskType;
    @Size(max = 45)
    @Column(name = "taskFormat")
    private String taskFormat;
    @Lob
    @Size(max = 65535)
    @Column(name = "taskDescription")
    private String taskDescription;
    @Size(max = 45)
    @Column(name = "taskIdentifier")
    private String taskIdentifier;
    @Size(max = 45)
    @Column(name = "taskPlan")
    private String taskPlan;
    @Lob
    @Size(max = 65535)
    @Column(name = "nextTask")
    private String nextTask;
    @Size(max = 45)
    @Column(name = "taskcol")
    private String taskcol;
    @Column(name = "repetitionValue")
    private Integer repetitionValue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskID")
    private List<TemporalElement> temporalElementList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskID")
    private List<Event> eventList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtask")
    private List<NonMedication> nonMedicationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtask")
    private List<Observation> observationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtask")
    private List<Medication> medicationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtask")
    private List<Procedure> procedureList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtask")
    private List<Exam> examList;
    @JoinColumn(name = "idguideexec", referencedColumnName = "idguideexec")
    @ManyToOne(optional = false)
    private GuideExec idguideexec;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtask")
    private List<ConditionSet> conditionSetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtask")
    private List<Formula> formulaList;

    public Task() {
    }

    public Task(Integer idtask) {
        this.idtask = idtask;
    }

    public Task(Integer idtask, Date time) {
        this.idtask = idtask;
        this.time = time;
    }

    public Integer getIdtask() {
        return idtask;
    }

    public void setIdtask(Integer idtask) {
        this.idtask = idtask;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskFormat() {
        return taskFormat;
    }

    public void setTaskFormat(String taskFormat) {
        this.taskFormat = taskFormat;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskIdentifier() {
        return taskIdentifier;
    }

    public void setTaskIdentifier(String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public String getTaskPlan() {
        return taskPlan;
    }

    public void setTaskPlan(String taskPlan) {
        this.taskPlan = taskPlan;
    }

    public String getNextTask() {
        return nextTask;
    }

    public void setNextTask(String nextTask) {
        this.nextTask = nextTask;
    }

    public String getTaskcol() {
        return taskcol;
    }

    public void setTaskcol(String taskcol) {
        this.taskcol = taskcol;
    }

    public Integer getRepetitionValue() {
        return repetitionValue;
    }

    public void setRepetitionValue(Integer repetitionValue) {
        this.repetitionValue = repetitionValue;
    }

    @XmlTransient
    @JsonIgnore
    public List<TemporalElement> getTemporalElementList() {
        return temporalElementList;
    }

    public void setTemporalElementList(List<TemporalElement> temporalElementList) {
        this.temporalElementList = temporalElementList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    @XmlTransient
    @JsonIgnore
    public List<NonMedication> getNonMedicationList() {
        return nonMedicationList;
    }

    public void setNonMedicationList(List<NonMedication> nonMedicationList) {
        this.nonMedicationList = nonMedicationList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Observation> getObservationList() {
        return observationList;
    }

    public void setObservationList(List<Observation> observationList) {
        this.observationList = observationList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Medication> getMedicationList() {
        return medicationList;
    }

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Procedure> getProcedureList() {
        return procedureList;
    }

    public void setProcedureList(List<Procedure> procedureList) {
        this.procedureList = procedureList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    public GuideExec getIdguideexec() {
        return idguideexec;
    }

    public void setIdguideexec(GuideExec idguideexec) {
        this.idguideexec = idguideexec;
    }

    @XmlTransient
    @JsonIgnore
    public List<ConditionSet> getConditionSetList() {
        return conditionSetList;
    }

    public void setConditionSetList(List<ConditionSet> conditionSetList) {
        this.conditionSetList = conditionSetList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Formula> getFormulaList() {
        return formulaList;
    }

    public void setFormulaList(List<Formula> formulaList) {
        this.formulaList = formulaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtask != null ? idtask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.idtask == null && other.idtask != null) || (this.idtask != null && !this.idtask.equals(other.idtask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.compguide.Persistence.Entities.Task[ idtask=" + idtask + " ]";
    }
    
}
