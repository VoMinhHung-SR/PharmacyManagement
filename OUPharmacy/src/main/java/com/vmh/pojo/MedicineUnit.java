/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "medicine_unit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicineUnit.findAll", query = "SELECT m FROM MedicineUnit m"),
    @NamedQuery(name = "MedicineUnit.findById", query = "SELECT m FROM MedicineUnit m WHERE m.id = :id"),
    @NamedQuery(name = "MedicineUnit.findByInStock", query = "SELECT m FROM MedicineUnit m WHERE m.inStock = :inStock"),
    @NamedQuery(name = "MedicineUnit.findByPrice", query = "SELECT m FROM MedicineUnit m WHERE m.price = :price"),
    @NamedQuery(name = "MedicineUnit.findByImage", query = "SELECT m FROM MedicineUnit m WHERE m.image = :image")})
public class MedicineUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "{medicineUnit.inStock.nullErr}")
    @Column(name = "in_stock")
    private int inStock;
    @Basic(optional = false)
    @NotNull(message = "{medicineUnit.price.nullErr}")
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Category categoryId;
    @JoinColumn(name = "medicine_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Medicine medicineId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicineUnitId")
    private Collection<PrescriptionDetail> prescriptionDetailCollection;

    @Transient
    @JsonIgnore
    private MultipartFile file;
    
    public MedicineUnit() {
    }

    public MedicineUnit(Integer id) {
        this.id = id;
    }

    public MedicineUnit(Integer id, int inStock, double price, String image) {
        this.id = id;
        this.inStock = inStock;
        this.price = price;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Medicine getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Medicine medicineId) {
        this.medicineId = medicineId;
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
        if (!(object instanceof MedicineUnit)) {
            return false;
        }
        MedicineUnit other = (MedicineUnit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vmh.pojo.MedicineUnit[ id=" + id + " ]";
    }

    /**
     * @return the file
     */
    @JsonIgnore
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
