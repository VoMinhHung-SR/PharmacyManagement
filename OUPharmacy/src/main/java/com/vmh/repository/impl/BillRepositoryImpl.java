/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository.impl;

import com.vmh.pojo.Bill;
import com.vmh.repository.BillRepository;
import org.hibernate.Session;
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
public class BillRepositoryImpl implements BillRepository{

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    
    @Override
    public boolean addBill(Bill b) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(b);
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }
    
    
}
