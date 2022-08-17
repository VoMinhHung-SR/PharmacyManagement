/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.Prescription;
import com.vmh.service.PrescriptionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ApiPrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    
    @GetMapping(path="/booking/{bookingId}/prescription",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Prescription>> getPrescription(Model model, 
            Map<String,String> params,
            @PathVariable(value = "bookingId") int examinationDetailId){
         try {
               return new ResponseEntity<>(
                       this.prescriptionService.getPrescriptionByExaminationDetailId(params, examinationDetailId),
                       HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    //add-prescription
    @PostMapping(path = "/prescription", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription prescription) {

        HttpStatus status = null;
        try {
            Prescription p = this.prescriptionService.addPrescription(prescription);

            status = HttpStatus.CREATED;
            return new ResponseEntity<>(p, status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(status);
    }
}
