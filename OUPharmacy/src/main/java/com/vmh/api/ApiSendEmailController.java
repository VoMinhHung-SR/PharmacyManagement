/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.User;
import com.vmh.service.EmailService;
import com.vmh.service.UserService;
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
     
    @PostMapping(path = "/send-email", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> sendEmailPatient(Model model,
            @RequestBody Map<String,String> params) {
        try {
            if (params != null && !params.get("userId").isEmpty()) {
  
                Integer uId = Integer.parseInt(params.get("userId"));

//                Locale lcl = Locale.VIE;  
//
//                // creating an object of the class Date  
//                Date d = new Date();  
//
//                // getting the instance by invoking the getDateInstance(int, Locale) method  
//                DateFormat dFormat = DateFormat.getDateInstance(DateFormat.SHORT, lcl);  
//
//                String str = dFormat.format(d);  
//                System.out.println(str);  
                User u = this.userService.getUserById(uId);
                String subject = "Thư xác nhận lịch đăng ký khám";
                Map<String, Object> map = new HashMap<>();
                map.put("user", u);
                if(this.emailService.sendMail(subject,
                        new String[]{u.getEmail()}, map)){
                    return new ResponseEntity<>(HttpStatus.OK);
                };
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
