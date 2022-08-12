/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository.impl;

import com.vmh.pojo.Examination;
import com.vmh.pojo.User;
import com.vmh.repository.ExaminationRepository;
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
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class ExaminationRepositoryImpl implements ExaminationRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Examination addExamination(Examination e) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(e);
            return e;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Examination> getExaminationByUserId(Map<String, String> params, int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Examination> q = builder.createQuery(Examination.class);
        Root root = q.from(Examination.class);

        Root userRoot = q.from(User.class);
        // +Lay tat ca dong du lieu
        q = q.select(root);

        List<Predicate> predicates = new ArrayList<>();

        Predicate p = builder.equal(userRoot.get("id").as(Integer.class), id);
        Predicate p1 = builder.equal(root.get("userExaminationId"), userRoot.get("id"));
        predicates.add(p);
        predicates.add(p1);

        if (params != null) {

            if (params.containsKey("kw") == true) {
                Predicate p2 = builder.like(root.get("description").as(String.class),
                        String.format("%%%s%%", params.get("kw")));
                predicates.add(p2);
            }
        }

        q = q.where(predicates.toArray(new Predicate[]{}));

        q = q.orderBy(builder.desc(root.get("id")));

        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean deleteExamination(int i) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            Examination e = session.get(Examination.class, i);
            session.delete(e);
            return true;
        }catch(HibernateException ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Examination> getExaminations() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Examination> q = builder.createQuery(Examination.class);
        Root root = q.from(Examination.class);
        
        q = q.select(root);
        q = q.orderBy(builder.asc(root.get("createdDate")));

        Query query = session.createQuery(q);
        
        return query.getResultList();
    }

    
    @Override
    public Examination getExaminationById(int examinationId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Examination> query = builder.createQuery(Examination.class);
            Root<Examination> root = query.from(Examination.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), examinationId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
