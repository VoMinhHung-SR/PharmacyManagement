/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.service.ExaminationDetailService;
import com.vmh.service.PatientService;
import com.vmh.service.PrescriptionService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
public class PatientController {
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private PrescriptionService prescriptionService;
    
    @Autowired
    private ExaminationDetailService examinationDetailService;
    

    @GetMapping(path="/patients")
    public String getPatients(Model model,@RequestParam(required = false) Map<String, String> params){
        
        int page;
        if(params.get("page") != null && !params.get("page").isEmpty())
            page = Integer.parseInt(params.get("page"));
        else
            page = 1;   
        
        model.addAttribute("patients", this.patientService.getPatients(params));
        model.addAttribute("patientCounter", this.patientService.countPatient());
        
        return "patients";  
    }
    
    //    Benh an
    @GetMapping(path="/patients/{patientId}/medical-records/")
    public String getPatientMedicalRecords(Model model,@RequestParam(required = false) Map<String, String> params,
            @PathVariable(value = "patientId") int patientId){
        
        int page;
        
        if(params.get("page") != null && !params.get("page").isEmpty())
            page = Integer.parseInt(params.get("page"));
        else page = 1; 
        
        try{
            model.addAttribute("option", 1);
            model.addAttribute("patient", this.patientService.getPatientById(patientId));
            model.addAttribute("patientMedicalRecords", 
                    this.prescriptionService.getPrescriptionByPatientId(params, patientId));
            return "medical-records";
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        model.addAttribute("errMgs","Da co loi xay ra. Vui long quay lai sau!!");
        return "medical-records";
    }
    
    //    Danh sach kham
    
    @GetMapping(path="/patients/{patientId}/booking-list/")
    public String getPatientBookingList(Model model,@RequestParam(required = false) Map<String, String> params,
            @PathVariable(value = "patientId") int patientId){
        
        int page;
        
        if(params.get("page") != null && !params.get("page").isEmpty())
            page = Integer.parseInt(params.get("page"));
        else page = 1; 
        
        try{
            model.addAttribute("option", 2);
            model.addAttribute("patient", this.patientService.getPatientById(patientId));
            model.addAttribute("patientExaminationDeatails", 
                    this.examinationDetailService.getExaminationsByPatientId(params, patientId));
            return "medical-records";
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        model.addAttribute("errMgs","Da co loi xay ra. Vui long quay lai sau!!");
        return "medical-records";
    }
}
