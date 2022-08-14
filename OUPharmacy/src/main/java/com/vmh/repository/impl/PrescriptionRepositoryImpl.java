/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository.impl;

import com.vmh.pojo.Patient;
import com.vmh.pojo.Prescription;
import com.vmh.repository.PrescriptionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
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
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private Environment env;

    @Override
    public Prescription addPrescription(Prescription p) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(p);
            return p;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Prescription> getPrescriptionByPatientId(Map<String, String> params, int patientId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Prescription> q = builder.createQuery(Prescription.class);
            Root root = q.from(Prescription.class);
            Root<Patient> patientRoot = q.from(Patient.class);

            q = q.select(root);

            List<Predicate> predicates = new ArrayList<>();
            Predicate p1 = builder.equal(patientRoot.get("id").as(Integer.class), patientId);
            Predicate p2 = builder.equal(root.get("patientId"), patientRoot.get("id"));
            predicates.add(p1);
            predicates.add(p2);
            if (params != null) {

                if (params.containsKey("kw") == true) {
                    Predicate p3 = builder.like(root.get("sign").as(String.class),
                            String.format("%%%s%%", params.get("kw")));
                    predicates.add(p3);
                }
            }
            
            q.where(predicates.toArray(new Predicate[]{}));
            q = q.orderBy(builder.desc(root.get("createdDate")));
            
            Query query = session.createQuery(q);
            
            if(query == null)
                return null;
            
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
    public Prescription getPrescriptionById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Prescription.class,id);
    }

}
