/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository;

import com.vmh.pojo.Prescription;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface PrescriptionRepository {
    Prescription addPrescription(Prescription presciption);
    List<Prescription> getPrescriptionByPatientId(
            Map<String, String> params,int patientId);
}
