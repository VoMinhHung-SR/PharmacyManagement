/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.Patient;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface PatientService {
    List<Patient> getPatients(Map<String,String> params);
    List<Patient> getAllPatients();
    Patient getPatientById(int id);
    Patient addPatient(Patient patient);
    int countPatient();
}
