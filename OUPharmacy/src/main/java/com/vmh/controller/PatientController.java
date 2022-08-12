/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.service.PatientService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
public class PatientController {
    
    @Autowired
    private PatientService patientService;
    
    @GetMapping(path="/patients")
    public String getPatients(Model model,@RequestParam(required = false) Map<String, String> params){
        
        int page;
        if(params.get("page") != null && !params.get("page").isEmpty() ){
            page = Integer.parseInt(params.get("page"));
        }else
            page = 1;   
        
        model.addAttribute("patients", this.patientService.getPatients(params));
        model.addAttribute("patientCounter", this.patientService.countPatient());
        
        return "patients";
        
    }
    
    
}
