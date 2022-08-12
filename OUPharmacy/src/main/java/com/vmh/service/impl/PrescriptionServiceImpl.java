/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.vmh.pojo.Prescription;
import com.vmh.repository.PrescriptionRepository;
import com.vmh.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ASUS
 */
public class PrescriptionServiceImpl implements PrescriptionService{

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    
    @Override
    public boolean addPrescription(Prescription p) {
        return this.prescriptionRepository.addPrescription(p);
    }
    
    
}
