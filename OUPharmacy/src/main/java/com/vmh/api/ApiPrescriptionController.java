/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.Prescription;
import com.vmh.service.PrescriptionService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    //add-prescription
    @PostMapping(path = "/prescription", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription prescription) {

        HttpStatus status = null;
        try {
            Prescription p = this.prescriptionService.addPrescription(prescription);

            if (p != null) {
                status = HttpStatus.CREATED;
                return new ResponseEntity<>(p, status);
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(status);
    }
}
