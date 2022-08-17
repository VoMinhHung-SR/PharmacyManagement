/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.service.ExaminationDetailService;
import com.vmh.service.PrescriptionDetailService;
import com.vmh.service.PrescriptionService;
import java.util.Map;
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
public class BillController {
    
    @Autowired
    private ExaminationDetailService examinationDetailService;
    
    @Autowired
    private PrescriptionDetailService prescriptionDetailService;
    
    
    @Autowired
    private PrescriptionService prescriptionService;
    
    @GetMapping("/patients/{patientId}/bill/")
    public String payView(Model model, @PathVariable(value = "patientId") int patientId , 
            Map<String,String> params){
        int page;
        if(params.get("page") != null && !params.get("page").isEmpty())
            page = Integer.parseInt(params.get("page"));
        else
            page = 1;   
        model.addAttribute("patientId", patientId);
        model.addAttribute("bookingList", this.examinationDetailService.getExaminationsByPatientId(params, patientId));
        model.addAttribute("prescriptions", this.prescriptionDetailService.getAllPrescriptionDetails());
        return "bill";
    }
}
