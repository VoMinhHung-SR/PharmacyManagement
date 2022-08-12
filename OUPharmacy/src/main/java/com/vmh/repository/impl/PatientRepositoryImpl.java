/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository.impl;

import com.vmh.pojo.Patient;
import com.vmh.repository.PatientRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Autowired
    private Environment env;

    @Override
    public List<Patient> getPatients(Map<String, String> params) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Patient> q = builder.createQuery(Patient.class);
        Root root = q.from(Patient.class);

        // +Lay tat ca dong du lieu
        q = q.select(root);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (params != null) {

            

            if (params.containsKey("kw") == true) {
                String kw = params.get("kw");
                Predicate predicate1 = builder.like(root.get("firstName").as(String.class), String.format("%%%s%%", kw));
                Predicate predicate2 = builder.like(root.get("lastName").as(String.class), String.format("%%%s%%", kw));

                predicates.add(builder.or(predicate1, predicate2));
            }

        }

        q.where(predicates.toArray(new Predicate[]{}));

        Query query = session.createQuery(q);

        int page = 1;
        int pageSize = Integer.parseInt(env.getProperty("page.size").toString());;
        if (params.containsKey("page") && !params.get("page").isEmpty()) {
            page = Integer.parseInt(params.get("page"));
        }

        int startPage = (page - 1) * pageSize;
        query.setMaxResults(pageSize);
        query.setFirstResult(startPage);

        return query.getResultList();
    }

    @Override
    public Patient getPatientById(int i) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Patient> query = builder.createQuery(Patient.class);
            Root<Patient> root = query.from(Patient.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), i));
            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int countPatient() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("SELECT COUNT(*) FROM Patient");

        return Integer.parseInt(q.getSingleResult().toString());
    }

}
