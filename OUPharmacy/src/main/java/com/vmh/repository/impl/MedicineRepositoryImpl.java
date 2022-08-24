/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository.impl;

import com.vmh.pojo.Medicine;
import com.vmh.repository.MedicineRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class MedicineRepositoryImpl implements MedicineRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean getMedicineByName(String name) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Medicine> q = builder.createQuery(Medicine.class);
        Root root = q.from(Medicine.class);
        // +Lay tat ca dong du lieu
        q = q.select(root);
        

        Predicate p = builder.equal(root.get("name").as(String.class), name);
        q = q.where(p);
        if (q != null) {
            return false;
        }

        return true;
    }

    @Override
    public List<Medicine> getMedicines() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("From Medicine");
        return q.getResultList();
    }

    @Override
    public boolean addMedicine(Medicine medicine) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (getMedicineByName(medicine.getName()) == false) {
                session.save(medicine);
                return true;
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Medicine getMedicineDetail(int i) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Medicine> q = builder.createQuery(Medicine.class);
        Root root = q.from(Medicine.class);
        // +Lay tat ca dong du lieu
        q = q.select(root);

        Predicate p = builder.equal(root.get("id").as(Integer.class), i);
        q = q.where(p);

        return session.createQuery(q).getSingleResult();
    }

    @Override
    public boolean getMedicineNameUnique(String medicineName) {

        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        String sql = "SELECT COUNT(id) FROM Medicine WHERE name=:name";
        Query query = session.createQuery(sql);
        query.setParameter("name", medicineName.trim());

        return (long)query.getSingleResult() > 0;
    
    }

}
