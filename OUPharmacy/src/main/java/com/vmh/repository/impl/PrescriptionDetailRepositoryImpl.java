/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository.impl;

import com.vmh.pojo.Patient;
import com.vmh.pojo.Prescription;
import com.vmh.pojo.PrescriptionDetail;
import com.vmh.repository.PrescriptionDetailRepository;
import com.vmh.repository.PrescriptionRepository;
import java.math.BigDecimal;
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
public class PrescriptionDetailRepositoryImpl implements PrescriptionDetailRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private Environment env;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public PrescriptionDetail addPrescriptionDetail(PrescriptionDetail p) {
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
    public List<PrescriptionDetail> getListPreDetailByPrescriptionId(Map<String, String> params, int prescriptionId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<PrescriptionDetail> q = builder.createQuery(PrescriptionDetail.class);
            Root root = q.from(PrescriptionDetail.class);
            Root<Prescription> prescriptionRoot = q.from(Prescription.class);

            q = q.select(root);

            List<Predicate> predicates = new ArrayList<>();
            Predicate p1 = builder.equal(prescriptionRoot.get("id").as(Integer.class), prescriptionId);
            Predicate p2 = builder.equal(root.get("prescriptionId"), prescriptionRoot.get("id"));
            predicates.add(p1);
            predicates.add(p2);
            if (params != null) {
                if (params.containsKey("fromQuantity") == true) {
                    BigDecimal fromQuantity = new BigDecimal(Double.parseDouble(params.get("fromPrice")));
                    Predicate p3 = builder.greaterThanOrEqualTo(root.get("quantity").as(String.class), fromQuantity);
                    predicates.add(p3);
                }

                if (params.containsKey("fromQuantity") == true) {
                    BigDecimal toQuantity = new BigDecimal(Double.parseDouble(params.get("toPrice")));
                    Predicate p4 = builder.lessThanOrEqualTo(root.get("quantity").as(String.class), toQuantity);
                    predicates.add(p4);
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
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PrescriptionDetail> getAllPrescriptionDetails() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("From PrescriptionDetail");
        return q.getResultList();
    }

}
