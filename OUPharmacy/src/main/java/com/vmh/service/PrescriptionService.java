/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.Prescription;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface PrescriptionService {
    Prescription addPrescription(Prescription p);
    Prescription getPrescriptionById(int prescriptionId);
    List<Prescription> getPrescriptionByPatientId(
            Map<String, String> params,int patientId);
}
