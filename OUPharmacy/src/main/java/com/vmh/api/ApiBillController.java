/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.Bill;
import com.vmh.service.BillService;
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
public class ApiBillController {
    
    @Autowired
    private BillService billService;
    
    @PostMapping(path="/prescriptions/{prescriptionId}/pay/", 
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Bill> pay(Model model, @RequestBody Bill bill){
        
        try{
            Bill b = this.billService.addBill(bill);
            return new ResponseEntity<>(b,HttpStatus.CREATED);
        }catch(Exception ex){
           ex.printStackTrace();
        }     
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(path="/prescriptions/{prescriptionId}/pay/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Bill>> receipt(Model model,
            Map<String,String> map,
            @PathVariable(value="prescriptionId") int prescriptionId){
        
        try{
            return new ResponseEntity<>(
                    this.billService.getBillByPrescriptionId(map, prescriptionId),
                    HttpStatus.OK);
        }catch(Exception ex){
           ex.printStackTrace();
        }     
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
