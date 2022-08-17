/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.vmh.pojo.Bill;
import com.vmh.repository.BillRepository;
import com.vmh.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillRepository billRepository;
    
    
    @Override
    public boolean addBill(Bill bill) {
        return this.billRepository.addBill(bill);
    }
    
}
