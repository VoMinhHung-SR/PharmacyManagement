/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository;

import com.vmh.pojo.Examination;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface ExaminationRepository {
    List<Examination> getExaminations();
    Examination getExaminationById(int id);
    List<Examination> getExaminationByUserId(Map<String, String> params,int id);
    Examination addExamination(Examination e);
    boolean deleteExamination(int id);
}
