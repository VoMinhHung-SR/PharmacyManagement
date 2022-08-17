/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "prescription")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prescription.findAll", query = "SELECT p FROM Prescription p"),
    @NamedQuery(name = "Prescription.findById", query = "SELECT p FROM Prescription p WHERE p.id = :id"),
    @NamedQuery(name = "Prescription.findBySign", query = "SELECT p FROM Prescription p WHERE p.sign = :sign"),
    @NamedQuery(name = "Prescription.findByDiagnosed", query = "SELECT p FROM Prescription p WHERE p.diagnosed = :diagnosed"),
    @NamedQuery(name = "Prescription.findByCreatedDate", query = "SELECT p FROM Prescription p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "Prescription.findByActive", query = "SELECT p FROM Prescription p WHERE p.active = :active")})
public class Prescription implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "sign")
    private String sign;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "diagnosed")
    private String diagnosed;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "active")
    private Short active;
    @JsonIgnoreProperties({"isSuperuser","username", "firstName", "lastName", "gender",
    "avatar", "dateOfBirth", "phoneNumber", "email", "isActive", "address","userRole"})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "examination_detail_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"prescriptionCollection", "wage", "examinationId", "patientId"})
    @ManyToOne(optional = false)
    private ExaminationDetail examinationDetailId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prescriptionBillId")
    private Collection<Bill> billCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prescriptionId")
    private Collection<PrescriptionDetail> prescriptionDetailCollection;

    public Prescription() {
    }

    public Prescription(Integer id) {
        this.id = id;
    }

    public Prescription(Integer id, String sign, String diagnosed) {
        this.id = id;
        this.sign = sign;
        this.diagnosed = diagnosed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDiagnosed() {
        return diagnosed;
    }

    public void setDiagnosed(String diagnosed) {
        this.diagnosed = diagnosed;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        if(createdDate!= null)
            this.createdDate = createdDate;
        this.createdDate = new Date();
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Bill> getBillCollection() {
        return billCollection;
    }

    public void setBillCollection(Collection<Bill> billCollection) {
        this.billCollection = billCollection;
    }

    @XmlTransient
    public Collection<PrescriptionDetail> getPrescriptionDetailCollection() {
        return prescriptionDetailCollection;
    }

    public void setPrescriptionDetailCollection(Collection<PrescriptionDetail> prescriptionDetailCollection) {
        this.prescriptionDetailCollection = prescriptionDetailCollection;
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
        if (!(object instanceof Prescription)) {
            return false;
        }
        Prescription other = (Prescription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vmh.pojo.Prescription[ id=" + id + " ]";
    }

    public ExaminationDetail getExaminationDetailId() {
        return examinationDetailId;
    }

    public void setExaminationDetailId(ExaminationDetail examinationDetailId) {
        this.examinationDetailId = examinationDetailId;
    }
    
}
