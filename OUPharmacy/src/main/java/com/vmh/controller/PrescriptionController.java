/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

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
     
    @GetMapping(path="/patients/{patientId}/add-prescription/")
    public String addPatientPrescriptionView(Model model,
            @PathVariable(value = "patientId") int patientId){
        model.addAttribute("patient", this.patientService.getPatientById(patientId));
        return "prescription";
    }
}
