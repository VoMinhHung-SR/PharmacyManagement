/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.ExaminationDetail;
import com.vmh.service.ExaminationDetailService;
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
public class ApiExaminationDetailController {
    
    @Autowired
    private ExaminationDetailService examinationDetailService;
    
    @GetMapping(path="/examination-detail", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ExaminationDetail>> getExaminationsDetail(Model model){
        try {
               return new ResponseEntity<>(
                       this.examinationDetailService.getListExaminationDetails(),
                       HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(path="/booking-list/patients/{patientId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ExaminationDetail>> getExaminationDetailById(Model model,
            @PathVariable(value="patientId") int id,
            Map<String,String> params){
        try {
               return new ResponseEntity<>(
                       this.examinationDetailService.getExaminationsByPatientId(params, id),
                       HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping(path="/examination-detail", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ExaminationDetail> addExaminationDetail(Model model,
            @RequestBody ExaminationDetail examinationDetail){
        try {
               ExaminationDetail ed = this.examinationDetailService.addExaminationDetail(examinationDetail);
               return new ResponseEntity<>(ed,HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
