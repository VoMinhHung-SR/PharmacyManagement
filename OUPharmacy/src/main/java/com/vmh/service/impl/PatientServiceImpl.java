/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.vmh.pojo.Patient;
import com.vmh.repository.PatientRepository;
import com.vmh.service.PatientService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;
    
    @Override
    public List<Patient> getPatients(Map<String, String> params) {
        
        return this.patientRepository.getPatients(params);
    }
    @Override
    public Patient getPatientById(int i) {
        return this.patientRepository.getPatientById(i);
    }
    @Override
    public int countPatient() {
        return this.patientRepository.countPatient();
    }

    @Override
    public Patient addPatient(Patient ptnt) {
        return this.patientRepository.addPatient(ptnt);
    }

    @Override
    public List<Patient> getAllPatients() {
        return this.patientRepository.getAllPatients();
    }
    
}
