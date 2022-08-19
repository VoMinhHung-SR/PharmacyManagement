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
    public List<Prescription> getPrescriptionByExaminationDetailId(Map<String, String> params, int examinationDetailId) {
        return this.prescriptionRepository.getPrescriptionByExaminationDetailId(params, examinationDetailId);
    }

    @Override
    public Prescription getPrescriptionById(int prescriptionId) {
        return this.prescriptionRepository.getPrescriptionById(prescriptionId);
    }

    @Override
    public List<Prescription> getPrescriptions(Map<String, String> map) {
        return this.prescriptionRepository.getPrescriptions(map);
    }
    
    
}
