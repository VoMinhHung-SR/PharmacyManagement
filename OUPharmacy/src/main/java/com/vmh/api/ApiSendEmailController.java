/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.constant.EmailConstant;
import com.vmh.pojo.Examination;
import com.vmh.pojo.User;
import com.vmh.service.EmailService;
import com.vmh.service.ExaminationService;
import com.vmh.service.UserService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
public class ApiSendEmailController {
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ExaminationService examinationService;
     
    @PostMapping(path = "/send-email", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> sendEmailPatient(
            @RequestBody Map<String,String> params) {
        try {
            if (params != null && !params.get("userId").isEmpty()
                    && !params.get("examinationId").isEmpty()) {
  
                Date date = new Date();
                SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
                
                
                Integer uId = Integer.parseInt(params.get("userId"));
                Integer eId = Integer.parseInt(params.get("examinationId"));
                
                User user = this.userService.getUserById(uId);
                Examination examination = this.examinationService.getExaminationById(eId);
                
                String subject = "Thư xác nhận lịch đăng ký khám";
                String stringDate= DateFor.format(date);
                String stringDate2 = DateFor.format(examination.getCreatedDate());
                
                Map<String, Object> model = new HashMap<>();
                model.put("user", user);
                model.put("examination", examination);
                model.put("date", stringDate);
                model.put("createdDate", stringDate2);
                
                
                if(this.emailService.sendMail(subject,
                        new String[]{user.getEmail()}, model, EmailConstant.EMAIL_TEMPLATE_SENDER)){
                    return new ResponseEntity<>(HttpStatus.OK);
                };
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PostMapping(path = "/send-email-add-schedule", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> sendEmailAddSchedule(
            @RequestBody Map<String,String> params) {
        try {
            
            if (params != null && !params.get("userId").isEmpty()
                    && !params.get("date").isEmpty() && !params.get("shift").isEmpty()) {
                
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(params.get("date")); 
                
                SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");

                
                Integer uId = Integer.parseInt(params.get("userId"));
                User user = this.userService.getUserById(uId);
              
               
                String subject = "Thư thông báo lịch trực";
                int shift = Integer.parseInt(params.get("shift"));
                String stringDate= DateFor.format(date);
                
                Map<String, Object> model = new HashMap<>();
                model.put("user", user);
                model.put("date", stringDate);
                model.put("shift", shift);
                
                
                if(this.emailService.sendMail(subject,
                        new String[]{user.getEmail()}, model, 
                        EmailConstant.EMAIL_TEMPLATE_ADD_SCHEDULE)){
                    return new ResponseEntity<>(HttpStatus.OK);
                };
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
