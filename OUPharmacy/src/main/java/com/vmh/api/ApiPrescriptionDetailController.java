/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.MedicineUnit;
import com.vmh.pojo.Prescription;
import com.vmh.pojo.PrescriptionDetail;
import com.vmh.service.MedicineUnitService;
import com.vmh.service.PrescriptionDetailService;
import com.vmh.service.PrescriptionService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
public class ApiPrescriptionDetailController {

    @Autowired
    private PrescriptionDetailService prescriptionDetailService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private MedicineUnitService medicineUnitService;

    @PostMapping(path = "/prescription-detail",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PrescriptionDetail> addPrescriptionDetail(Model model,
            @RequestBody PrescriptionDetail prescriptionDetail) {
        HttpStatus status = null;
        try {
            PrescriptionDetail p = this.prescriptionDetailService.addPrescriptionDetail(prescriptionDetail);
            if (p != null) {
                status = HttpStatus.CREATED;
                return new ResponseEntity<>(p, status);
            } else {
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(status);
    }

}
