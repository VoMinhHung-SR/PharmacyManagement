/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.Examination;
import com.vmh.pojo.User;
import com.vmh.service.ExaminationService;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class ApiBookingController {
    @Autowired
    private ExaminationService examinationService;
    
    
    
    
    @PostMapping(path="/booking", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Examination> addExamination(@RequestBody Examination e, 
            HttpSession session){
        
        User creator = (User) session.getAttribute("currentUser");
        
        if(creator != null)
            try{  
                Examination n = this.examinationService.addExamination(e);
                return new ResponseEntity<>(n, HttpStatus.CREATED);
            }catch(Exception ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    
    @DeleteMapping("/booking/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable(value="bookingId") int id){
        this.examinationService.deleteExamination(id);
    }
}
