/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

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
    
    @GetMapping(path="/patients/{patientId}/add-prescription/")
    public String addPatientPrescriptionView(Model model,
            @PathVariable(value = "patientId") int patientId){
        
        return "prescription";
    }
}
