/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "prescription_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrescriptionDetail.findAll", query = "SELECT p FROM PrescriptionDetail p"),
    @NamedQuery(name = "PrescriptionDetail.findById", query = "SELECT p FROM PrescriptionDetail p WHERE p.id = :id"),
    @NamedQuery(name = "PrescriptionDetail.findByQuantity", query = "SELECT p FROM PrescriptionDetail p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "PrescriptionDetail.findByUses", query = "SELECT p FROM PrescriptionDetail p WHERE p.uses = :uses")})
public class PrescriptionDetail implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "uses")
    private String uses;
    @JoinColumn(name = "medicine_unit_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"inStock", "image"})
    private MedicineUnit medicineUnitId;
    @JoinColumn(name = "prescription_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"sign","diagnosed","createdDate","active","patientId","userId"})
    private Prescription prescriptionId;

    public PrescriptionDetail() {
    }

    public PrescriptionDetail(Integer id) {
        this.id = id;
    }

    public PrescriptionDetail(Integer id, int quantity, String uses) {
        this.id = id;
        this.quantity = quantity;
        this.uses = uses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }

    public MedicineUnit getMedicineUnitId() {
        return medicineUnitId;
    }

    public void setMedicineUnitId(MedicineUnit medicineUnitId) {
        this.medicineUnitId = medicineUnitId;
    }

    public Prescription getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Prescription prescriptionId) {
        this.prescriptionId = prescriptionId;
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
        if (!(object instanceof PrescriptionDetail)) {
            return false;
        }
        PrescriptionDetail other = (PrescriptionDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vmh.pojo.PrescriptionDetail[ id=" + id + " ]";
    }
    
}
