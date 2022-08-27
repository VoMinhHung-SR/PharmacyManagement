/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
public interface CloudinaryService {
    public String uploadAvatar(MultipartFile file);
}
