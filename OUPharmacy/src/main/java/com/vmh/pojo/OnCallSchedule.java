/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "on_call_schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OnCallSchedule.findAll", query = "SELECT o FROM OnCallSchedule o"),
    @NamedQuery(name = "OnCallSchedule.findById", query = "SELECT o FROM OnCallSchedule o WHERE o.id = :id"),
    @NamedQuery(name = "OnCallSchedule.findByShift", query = "SELECT o FROM OnCallSchedule o WHERE o.shift = :shift"),
    @NamedQuery(name = "OnCallSchedule.findByCreatedDate", query = "SELECT o FROM OnCallSchedule o WHERE o.createdDate = :createdDate")})
public class OnCallSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "shift")
    private int shift;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "user_on_call_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userOnCallId;

    public OnCallSchedule() {
    }

    public OnCallSchedule(Integer id) {
        this.id = id;
    }

    public OnCallSchedule(Integer id, int shift, Date createdDate) {
        this.id = id;
        this.shift = shift;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUserOnCallId() {
        return userOnCallId;
    }

    public void setUserOnCallId(User userOnCallId) {
        this.userOnCallId = userOnCallId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OnCallSchedule)) {
            return false;
        }
        OnCallSchedule other = (OnCallSchedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vmh.pojo.OnCallSchedule[ id=" + id + " ]";
    }
    
}
