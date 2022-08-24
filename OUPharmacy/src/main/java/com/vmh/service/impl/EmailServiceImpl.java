/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;
import com.vmh.service.EmailService;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service("emailService")
@PropertySource("classpath:database.properties")
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @Override
    public boolean sendMail(String to, String subject, String content) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(environment.getProperty("default.from.email"));
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(content);
  
            javaMailSender.send(mailMessage);
            
            return true;
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

}
