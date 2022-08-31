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
public class ApiPrescriptionDetailController {

    @Autowired
    private PrescriptionDetailService prescriptionDetailService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private MedicineUnitService medicineUnitService;

    @GetMapping(path="/prescription-detail/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<PrescriptionDetail>> getPrescriptionDetails(Model model, 
            Map<String,String> params){
        try {
               return new ResponseEntity<>(
                       this.prescriptionDetailService.getAllPrescriptionDetails(),
                       HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    
    @GetMapping(path="/prescription-detail/{prescriptionDetailId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<PrescriptionDetail>> getPrescriptionDetailById(Model model, 
            @PathVariable(value="prescriptionDetailId") int id,
            Map<String,String> params){
        try {
               return new ResponseEntity<>(
                       this.prescriptionDetailService.getListPreDetailByPrescriptionId(params,id),
                       HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    
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
        
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
