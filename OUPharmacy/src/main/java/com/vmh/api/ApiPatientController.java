/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.Patient;
import com.vmh.service.PatientService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class ApiPatientController {
    
    @Autowired
    private PatientService patientService;
    
    @GetMapping(path="/patients" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Patient>> getPatients (Model model){
        try{
            return new ResponseEntity<>(this.patientService.getAllPatients(), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(path="/patient" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Patient> addPatient (Model model, @RequestBody Patient p){
        try{
            Patient patient = this.patientService.addPatient(p);
            return new ResponseEntity<>(patient, HttpStatus.CREATED);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
