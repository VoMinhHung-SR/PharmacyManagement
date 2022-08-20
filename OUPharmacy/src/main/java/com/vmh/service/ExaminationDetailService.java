/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.ExaminationDetail;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface ExaminationDetailService {
    ExaminationDetail getExaminationDetail(int examinationDetailId);
    ExaminationDetail getExaminationDetailByExaminationId(int examinationId);
    List<ExaminationDetail> getExaminationsByPatientId(Map<String, String> params,int patientId);
}
