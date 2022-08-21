/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository;

import com.vmh.pojo.ExaminationDetail;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface ExaminationDetailRepository {
    List<ExaminationDetail> getListExaminationDetails();
    List<ExaminationDetail> getExaminationsByPatientId(Map<String, String> params,int patientId);
    ExaminationDetail getExaminationDetail(int id);
    ExaminationDetail addExaminationDetail(ExaminationDetail ex);
    ExaminationDetail getExaminationDetailByExaminationId(int examinationId);
}
