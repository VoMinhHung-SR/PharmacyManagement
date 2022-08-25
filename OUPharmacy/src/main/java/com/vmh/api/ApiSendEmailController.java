/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.User;
import com.vmh.service.EmailService;
import com.vmh.service.UserService;
import java.util.HashMap;
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
     
    @PostMapping(path = "/send-email", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> sendEmailPatient(Model model,
            @RequestBody Map<String,String> params) {
        try {
            if (params != null && !params.get("userId").isEmpty()) {
                  Integer uId = Integer.parseInt(params.get("userId"));
//                String to = params.get("toUser");
//                String subject = params.get("subject");
//                String content = params.get("content");
//                if (this.emailService.sendMail(to, subject, content)) {
//                    return new ResponseEntity<>(HttpStatus.OK);
//                }
                User u = this.userService.getUserById(uId);
                String subject = "Thư xác nhận lịch đăng ký khám";
                
                if(this.emailService.sendMail(subject,
                        new String[]{u.getEmail()}, u)){
                    return new ResponseEntity<>(HttpStatus.OK);
                };
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
