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
    Prescription getPrescriptionById(int id);
    List<Prescription> getPrescriptions(Map<String, String> params);
    List<Prescription> getPrescriptionByExaminationDetailId(
            Map<String, String> params,int examinationDetailId);
}
