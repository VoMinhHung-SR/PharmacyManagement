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

        List<Predicate> predicates = new ArrayList<>();

        if (params != null) {
            if (params.containsKey("kw") == true) {
                String kw = params.get("kw");
                Predicate predicate1 = builder.like(root.get("firstName").as(String.class), String.format("%%%s%%", kw));
                Predicate predicate2 = builder.like(root.get("lastName").as(String.class), String.format("%%%s%%", kw));
                Predicate p1 = builder.like(root.get("username").as(String.class), String.format("%%%s%%", params.get("name")));
                predicates.add(builder.or(predicate1, predicate2, p1));
            }
        }

        Predicate p = builder.equal(root.get("userRole").as(String.class), userRole.trim());
        predicates.add(p);

        q = q.where(predicates.toArray(new Predicate[]{}));

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
    public int countUsersByUserRole(String userRole) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("SELECT COUNT(*) FROM User Where userRole = '" + userRole + "'");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public int countUser() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("SELECT COUNT(*) FROM User");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public List<User> getUserNotAdmin(Map<String, String> params) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> q = builder.createQuery(User.class);
        Root root = q.from(User.class);
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
        Predicate predicate = builder.notEqual(root.get("userRole").as(String.class), "ROLE_ADMIN");

        predicates.add(builder.or(predicate));

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
    public int countUserWithoutAdmin() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            Query q = session.createQuery("SELECT COUNT(*) FROM User Where userRole != 'ROLE_ADMIN'");
            return Integer.parseInt(q.getSingleResult().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;

    }

    @Override
    public boolean editAdminUser(int i) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            User u = getUserById(i);
            u.setUserRole("ROLE_ADMIN");
            session.save(u);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public User getUserById(int i) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), i));
            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean getUniqueUserName(String username) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM User WHERE username=:username";
        Query query = session.createQuery(sql);
        query.setParameter("username", username.trim());

        return (long) query.getSingleResult() > 0;
    }

    @Override
    public boolean getUnitqueEmail(String email) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM User WHERE email=:email";
        Query query = session.createQuery(sql);
        query.setParameter("email", email.trim());

        return (long) query.getSingleResult() > 0;
    }

    @Override
    public boolean setActiveUser(int userId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {

            User u = getUserById(userId);
            Short i = u.getIsActive();
            if (u.getIsActive() == 0) {
                i = 1;
                u.setIsActive(i);
            } else if (u.getIsActive() == 1) {
                i = 0;
                u.setIsActive(i);
            }
            session.save(u);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateUser(User u, int userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try {
            User user = session.get(User.class, userId);
            user.setFirstName(u.getFirstName());
            user.setLastName(u.getLastName());
            user.setUsername(u.getUsername());
            user.setPassword(u.getPassword());
            user.setEmail(u.getEmail());
            user.setDateOfBirth(u.getDateOfBirth());
            user.setPhoneNumber(u.getPhoneNumber());
            user.setGender(u.getGender());
            user.setAddress(u.getAddress());
           
            if (u.getAvatar() != null)
                user.setAvatar(u.getAvatar());
            
            session.update(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
