/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface EmailService {
    public boolean sendMail(String to, String subject, String content);
}
