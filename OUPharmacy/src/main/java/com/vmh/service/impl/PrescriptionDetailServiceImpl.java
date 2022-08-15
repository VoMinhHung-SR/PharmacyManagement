/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;
import com.vmh.pojo.PrescriptionDetail;
import com.vmh.repository.PrescriptionDetailRepository;
import com.vmh.service.PrescriptionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class PrescriptionDetailServiceImpl implements PrescriptionDetailService{
    
    @Autowired 
    private PrescriptionDetailRepository prescriptionDetailRepository;
    
    @Override
    public PrescriptionDetail addPrescriptionDetail(PrescriptionDetail p) {
        return this.prescriptionDetailRepository.addPrescriptionDetail(p);
    }


}
