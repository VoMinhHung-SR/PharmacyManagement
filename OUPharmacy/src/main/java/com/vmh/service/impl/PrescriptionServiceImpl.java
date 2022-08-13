/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.vmh.pojo.Prescription;
import com.vmh.repository.PrescriptionRepository;
import com.vmh.service.PrescriptionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class PrescriptionServiceImpl implements PrescriptionService{

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    
    @Override
    public Prescription addPrescription(Prescription p) {
        return this.prescriptionRepository.addPrescription(p);
    }

    @Override
    public List<Prescription> getPrescriptionByPatientId(Map<String, String> params, int patientId) {
        return this.prescriptionRepository.getPrescriptionByPatientId(params, patientId);
    }
    
    
}
