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
import org.springframework.web.bind.annotation.PathVariable;
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
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String reg(Model model, @ModelAttribute(value = "user") User user) {
        if (user.getPassword().isEmpty()
                || !user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("errMgs", "Mat khau khong hop le");
        } else {
            if (this.userDetailsService.addUser(user) == true) {
                return "redirect:/login";
            }
            model.addAttribute("errMgs", "He thong gap loi!! VUI LONG QUAY LAI SAU");
        }
        return "register";
    }

    //   ==== Admin =====
    @GetMapping("/admin/users/{userRole}")
    public String listUsersView(Model model, @PathVariable(value = "userRole") String userRole) {

        model.addAttribute("thisRole", userRole);

        if (userRole.equals("role-1")) {
            userRole = "ROLE_DOCTOR";
        } else if (userRole.equals("role-2")) {
            userRole = "ROLE_NURSE";
        } else {
            model.addAttribute("errMgs", "Role user khong hop le!!");
        }

        model.addAttribute("usersByUserRole", this.userDetailsService.getUserByUserRole(userRole));

        return "users";
    }

    @GetMapping("/admin/users/add-user/{userRole}")
    public String addUserView(Model model, @PathVariable(value = "userRole") String userRole) {
        model.addAttribute("user", new User());
        String thisRole = userRole;
        model.addAttribute("thisRole", thisRole);
        return "add-user";
    }

    @PostMapping("/admin/users/add-user/{userRole}")
    public String addUser(Model model, @PathVariable(value = "userRole") String userRole, 
            @ModelAttribute(value = "user") User user) {
        
        if (userRole.equals("role-1")) {
            userRole = "ROLE_DOCTOR";
        } else if (userRole.equals("role-2")) {
            userRole = "ROLE_NURSE";
        } else 
            model.addAttribute("errMgs", "Role user khong hop le!!");
 
        if (user.getPassword().isEmpty()
                || !user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("errMgs", "Mat khau khong hop le");
        } else {
           if (this.userDetailsService.addUserWithUserRole(user, userRole) == true) {
            return "redirect:/admin/dashboard";
           }
            model.addAttribute("errMgs", "He thong gap loi!! VUI LONG QUAY LAI SAU");
        }
        
        return "add-user";
    }
}
