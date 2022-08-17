/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.vmh.pojo.ExaminationDetail;
import com.vmh.repository.ExaminationDetailRepository;
import com.vmh.service.ExaminationDetailService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ExaminationDetailServiceImpl implements ExaminationDetailService{

    @Autowired
    private ExaminationDetailRepository examinationDetailRepository;
    
    @Override
    public List<ExaminationDetail> getExaminationsByPatientId(Map<String, String> params, int patientId) {
        return this.examinationDetailRepository.getExaminationsByPatientId(params, patientId);
    }

    @Override
    public ExaminationDetail getExaminationDetail(int i) {
        return this.examinationDetailRepository.getExaminationDetail(i);
    }
    
}
