/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "examination")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examination.findAll", query = "SELECT e FROM Examination e"),
    @NamedQuery(name = "Examination.findById", query = "SELECT e FROM Examination e WHERE e.id = :id"),
    @NamedQuery(name = "Examination.findByDescription", query = "SELECT e FROM Examination e WHERE e.description = :description"),
    @NamedQuery(name = "Examination.findByCreatedDate", query = "SELECT e FROM Examination e WHERE e.createdDate = :createdDate"),
    @NamedQuery(name = "Examination.findByActive", query = "SELECT e FROM Examination e WHERE e.active = :active")})
public class Examination implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "active")
    private Short active;
    @JsonIncludeProperties({"id"})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examinationId", fetch = FetchType.EAGER)
    private Collection<ExaminationDetail> examinationDetailCollection;
    @JoinColumn(name = "user_examination_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"isSuperuser", "username", "firstName", "lastName", "avatar",
        "gender", "dateOfBirth", "phoneNumber", "isActive", "address", "userRole"})
    private User userExaminationId;

    public Examination() {
    }

    public Examination(Integer id) {
        this.id = id;
    }

    public Examination(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<ExaminationDetail> getExaminationDetailCollection() {
        return examinationDetailCollection;
    }

    public void setExaminationDetailCollection(Collection<ExaminationDetail> examinationDetailCollection) {
        this.examinationDetailCollection = examinationDetailCollection;
    }

    public User getUserExaminationId() {
        return userExaminationId;
    }

    public void setUserExaminationId(User userExaminationId) {
        this.userExaminationId = userExaminationId;
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
        if (!(object instanceof Examination)) {
            return false;
        }
        Examination other = (Examination) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vmh.pojo.Examination[ id=" + id + " ]";
    }
    
}
