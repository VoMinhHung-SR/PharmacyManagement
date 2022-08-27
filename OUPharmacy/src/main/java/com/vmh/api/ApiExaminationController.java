/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.Examination;
import com.vmh.service.ExaminationService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class ApiExaminationController {
    @Autowired
    private ExaminationService examinationService;
    
    @GetMapping(path="/examinations",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Examination>> getListExamination(@RequestParam(required = false) Map<String,String> params){
        try{
               return new ResponseEntity<>(this.examinationService.getExaminations(params), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping(path="/examinations/{exminationId}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Examination> getListExaminationById(@PathVariable(value="exminationId") int exminationId){
        try{
            Examination e = this.examinationService.getExaminationById(exminationId);
               return new ResponseEntity<>(e, HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
