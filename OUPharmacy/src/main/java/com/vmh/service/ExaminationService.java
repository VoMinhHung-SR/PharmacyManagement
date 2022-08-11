/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.Examination;
import com.vmh.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface ExaminationService {
    Examination addExamination(String description, User creator);
    List<Examination> getExaminationByUserId(Map<String, String> params,int id);
    boolean deleteExamination(int id);
}
