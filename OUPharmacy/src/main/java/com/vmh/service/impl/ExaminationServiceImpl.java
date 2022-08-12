/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.vmh.pojo.Examination;
import com.vmh.pojo.User;
import com.vmh.repository.ExaminationRepository;
import com.vmh.repository.UserRepository;
import com.vmh.service.ExaminationService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class ExaminationServiceImpl implements ExaminationService{

    @Autowired
    private ExaminationRepository examinationRepository;

    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Examination addExamination(String description ,User creator) {
        
        Examination e = new Examination();
        e.setDescription(description);
        e.setUserExaminationId(creator);
//        Date d = new DateFormat.(createdDate);
//        e.setCreatedDate(d));
        return this.examinationRepository.addExamination(e);
    }

    @Override
    public List<Examination> getExaminationByUserId(Map<String, String> map, int i) {
       return this.examinationRepository.getExaminationByUserId(map, i);
    }

    @Override
    public boolean deleteExamination(int i) {
        return this.examinationRepository.deleteExamination(i);
    }

    @Override
    public List<Examination> getExaminations() {
        return this.examinationRepository.getExaminations();
    }

    @Override
    public Examination getExaminationById(int i) {
        return this.examinationRepository.getExaminationById(i);
    }
    
}
