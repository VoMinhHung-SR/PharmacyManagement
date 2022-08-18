/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository.impl;

import com.vmh.pojo.Bill;
import com.vmh.pojo.ExaminationDetail;
import com.vmh.pojo.Prescription;
import com.vmh.repository.BillRepository;
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
public class BillRepositoryImpl implements BillRepository{

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Autowired
    private Environment env;
    
    @Override
    public Bill addBill(Bill b) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(b);
            return b;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bill> getBillByPrescriptionId(Map<String, String> params, int prescriptionId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> q = builder.createQuery(Bill.class);
        Root root = q.from(Bill.class);
        Root<Prescription> prescriptionlRoot = q.from(Prescription.class);

        q = q.select(root);

        List<Predicate> predicates = new ArrayList<>();
        Predicate p1 = builder.equal(prescriptionlRoot.get("id").as(Integer.class),prescriptionId);
        Predicate p2 = builder.equal(root.get("prescriptionBillId"), prescriptionlRoot.get("id"));
        predicates.add(p1);
        predicates.add(p2);
        
        
//        if (params != null) {
//
//            if (params.containsKey("kw") == true) {
//                Predicate p3 = builder.like(root.get("sign").as(String.class),
//                        String.format("%%%s%%", params.get("kw")));
//                predicates.add(p3);
//            }
//        }

        q.where(predicates.toArray(new Predicate[]{}));

        Query query = session.createQuery(q);

        if (query == null) {
            return null;
        }

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
    
    
}
