/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository;

import com.vmh.pojo.Patient;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface PatientRepository {
    List<Patient> getPatients(Map<String,String> params);
    List<Patient> getAllPatients();
    Patient addPatient(Patient patient);
    Patient getPatientById(int id);
    int countPatient();
}
