/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.pojo.Examination;
import com.vmh.service.ExaminationDetailService;
import com.vmh.service.PatientService;
import com.vmh.service.PrescriptionService;
import java.util.Map;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
public class PrescriptionDetailController {
    
   @Autowired
   private PatientService patientService;
    
   @Autowired ExaminationDetailService examinationDetailService;
   
   
    @Autowired
    private PrescriptionService prescriptionService;
    
    @GetMapping("/patients/{patientId}/booking/{bookingId}/prescriptions/{prescriptionId}")
    public String addPrescriptionDetailView(Model model,
            @PathVariable(value="patientId") int patientId,
            @PathVariable(value="bookingId") int examinationDetailId,
            @PathVariable(value="prescriptionId") int prescriptionId){
        try{
            model.addAttribute("patient", this.patientService.getPatientById(patientId));
             model.addAttribute("examinationDetail", this.examinationDetailService.getExaminationDetail(examinationDetailId));
            model.addAttribute("prescription", this.prescriptionService.getPrescriptionById(prescriptionId));        
            return "prescription-detail";
        }catch(NoResultException exNoR){
            model.addAttribute("prescription", null);
        }
        model.addAttribute("errMgs", "Da co loi xay ra, vui long quay lai sau!!");      
       return "prescription-detail";   
    }
}
