/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository.impl;

import com.vmh.pojo.User;
import com.vmh.repository.UserRepository;
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
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private Environment env;

    @Override
    public boolean addUser(User user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(user);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<User> getUser(String username) {

        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> q = builder.createQuery(User.class);
        Root root = q.from(User.class);

        if (!username.isEmpty()) {
            Predicate p = builder.equal(root.get("username")
                    .as(String.class), username.trim());
            q = q.where(p);
        }
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<User> getUserByUserRole(Map<String, String> params,
            String userRole, int page) {

        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> q = builder.createQuery(User.class);
        Root root = q.from(User.class);
        // +Lay tat ca dong du lieu
        q = q.select(root);

        if (!userRole.isEmpty()) {
            Predicate p = builder.equal(root.get("userRole")
                    .as(String.class), userRole.trim());
            q = q.where(p);
        }

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            if (params.containsKey("name") == true) {
                Predicate p1 = builder.like(root.get("username").as(String.class),
                        String.format("%%%s%%", params.get("name")));
                predicates.add(p1);
            }
            if (params.containsKey("fn") == true) {
                Predicate p2 = builder.like(root.get("firstName").as(String.class),
                        String.format("%%%s%%", params.get("fn")));
                predicates.add(p2);
            }
            if (params.containsKey("ln") == true) {
                Predicate p3 = builder.like(root.get("lastName").as(String.class),
                        String.format("%%%s%%", params.get("ln")));
                predicates.add(p3);
            }
            
            Predicate p = builder.equal(root.get("userRole")
                    .as(String.class), userRole.trim());
            predicates.add(p);
            
            q = q.where(predicates.toArray(new Predicate[]{}));
        }

        Query query = session.createQuery(q);

        if (page > 0) {
            int size = Integer.parseInt(env.getProperty("page.size").toString());
            int start = (page - 1) * size;
            query.setFirstResult(start);
            query.setMaxResults(size);
        }

        return query.getResultList();
    }

    @Override
    public boolean addUserWithUserRole(User user, String string) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(user);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public int countUsersByUserRole(String string) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("SELECT COUNT(*) FROM User Where userRole =" + "'{string}'");

        return Integer.parseInt(q.getSingleResult().toString());
    }

}
