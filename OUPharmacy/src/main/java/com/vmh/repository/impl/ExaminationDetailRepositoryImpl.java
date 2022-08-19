/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository.impl;


import com.vmh.pojo.ExaminationDetail;
import com.vmh.pojo.Patient;
import com.vmh.repository.ExaminationDetailRepository;
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
public class ExaminationDetailRepositoryImpl implements ExaminationDetailRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private Environment env;

    @Override
    public List<ExaminationDetail> getExaminationsByPatientId(Map<String, String> params, int patientId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ExaminationDetail> q = builder.createQuery(ExaminationDetail.class);
            Root root = q.from(ExaminationDetail.class);
            Root<Patient> patientRoot = q.from(Patient.class);

            q = q.select(root);

            List<Predicate> predicates = new ArrayList<>();
            Predicate p1 = builder.equal(patientRoot.get("id").as(Integer.class), patientId);
            Predicate p2 = builder.equal(root.get("patientId"), patientRoot.get("id"));
            predicates.add(p1);
            predicates.add(p2);
            

            q.where(predicates.toArray(new Predicate[]{}));
            q = q.orderBy(builder.desc(root.get("id")));

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
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public ExaminationDetail getExaminationDetail(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ExaminationDetail> query = builder.createQuery(ExaminationDetail.class);
            Root<ExaminationDetail> root = query.from(ExaminationDetail.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), id));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
