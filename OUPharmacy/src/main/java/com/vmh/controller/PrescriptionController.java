/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.service.ExaminationDetailService;
import com.vmh.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author ASUS
 */
@Controller
public class PrescriptionController {
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    ExaminationDetailService examinationDetailService;
    
    @GetMapping(path = "/patients/{patientId}/booking/{bookingId}/add-prescription/")
    public String addPatientPrescriptionView(Model model,
            @PathVariable(value = "patientId") int patientId,
            @PathVariable(value = "bookingId") int examinationDetailId) {
        try {
            model.addAttribute("examinationDetail", this.examinationDetailService.getExaminationDetail(examinationDetailId));
            model.addAttribute("patient", this.patientService.getPatientById(patientId));
            return "prescription";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        model.addAttribute("errMgs", "Đã có lỗi xảy ra. Vui lòng quay lại sau!!!");
        return "prescription";
    }
}
