/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.handler;

import com.vmh.pojo.User;
import com.vmh.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author ASUS
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
    
    @Autowired
    private UserService userDetailService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        User u = this.userDetailService.getUser(authentication.getName()).get(0);
        
        request.getSession().setAttribute("currentUser", u);
        
        String redirectURL = request.getContextPath();
         
        if (u.hasRole("ROLE_ADMIN")) {
            redirectURL += "/admin/dashboard";
        } else if (u.hasRole("ROLE_DOCTOR") || 
                u.hasRole("ROLE_NURSE") ||
                u.hasRole("ROLE_PATIENT")) {
            redirectURL += "/";
        } 
        response.sendRedirect(redirectURL);
    }
}
