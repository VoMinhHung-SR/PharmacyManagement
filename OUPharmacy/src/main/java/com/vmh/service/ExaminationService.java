/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.Examination;
import com.vmh.pojo.User;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface ExaminationService {
    List<Examination> getExaminations(Map<String, String> params);
    Examination getExaminationById(int id);
    Examination addExamination(Examination e);
    List<Examination> getExaminationByUserId(Map<String, String> params,int id);
    boolean deleteExamination(int id);
    int countExamination();
}
