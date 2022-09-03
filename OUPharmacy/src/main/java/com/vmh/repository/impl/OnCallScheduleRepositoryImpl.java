/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository.impl;

import com.vmh.pojo.Bill;
import com.vmh.pojo.OnCallSchedule;
import com.vmh.pojo.Prescription;
import com.vmh.repository.OnCallScheduleRepository;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class OnCallScheduleRepositoryImpl implements OnCallScheduleRepository{

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public boolean addCalender(OnCallSchedule o) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(o);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public OnCallSchedule getSchedule(Date date) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OnCallSchedule> q = builder.createQuery(OnCallSchedule.class);
            Root root = q.from(OnCallSchedule.class);
            
            q = q.select(root);
            
            Predicate p = builder.equal(root.get("createdDate").as(Date.class), date);
            q = q.where(p);
            
            Query query = session.createQuery(q);
            
            return (OnCallSchedule) query.getSingleResult();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    
}
