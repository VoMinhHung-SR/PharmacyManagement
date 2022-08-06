/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.pojo.User;
import com.vmh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@Controller
public class UserController {
    
    @Autowired
    private UserService userDetailsService;
    
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String reg(Model model, @ModelAttribute(value = "user") User user) {
        if (user.getPassword().isEmpty() || 
                !user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("errMgs", "Mat khau khong hop le");
        } else {
            if (this.userDetailsService.addUser(user) == true) {
                return "redirect:/login";
            }
            model.addAttribute("errMgs", "He thong gap loi!! VUI LONG QUAY LAI SAU");
        }
        return "register";
    }
}
