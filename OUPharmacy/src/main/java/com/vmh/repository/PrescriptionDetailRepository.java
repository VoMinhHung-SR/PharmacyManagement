/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository;

import com.vmh.pojo.PrescriptionDetail;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface PrescriptionDetailRepository {
    List<PrescriptionDetail> getAllPrescriptionDetails();
    List<PrescriptionDetail> getListPreDetailByPrescriptionId(Map<String, String> params,int prescriptionId);
    PrescriptionDetail addPrescriptionDetail(PrescriptionDetail p);
}
