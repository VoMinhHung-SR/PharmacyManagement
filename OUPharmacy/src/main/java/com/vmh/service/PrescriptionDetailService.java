/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.MedicineUnit;
import com.vmh.pojo.Prescription;
import com.vmh.pojo.PrescriptionDetail;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface PrescriptionDetailService {
    List<PrescriptionDetail> getAllPrescriptionDetails();
    List<PrescriptionDetail> getListPreDetailByPrescriptionId(Map<String, String> params,int prescriptionId);
   PrescriptionDetail addPrescriptionDetail(PrescriptionDetail p); 
}
