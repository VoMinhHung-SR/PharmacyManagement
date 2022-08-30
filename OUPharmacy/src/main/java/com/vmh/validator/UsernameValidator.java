/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.validator;

import com.vmh.pojo.User;
import com.vmh.service.UserService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author ASUS
 */
public class UsernameValidator implements Validator{
    private final UserService userService;
    
    public UsernameValidator(UserService userService){
        this.userService = userService;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User u = (User) target;
  
        if( u.getId() == null &&  this.userService.getUniqueUserName(u.getUsername())){
            errors.rejectValue("username", 
                    "user.username.uniqueErr",
                    "Tài khoản đã tồn tại");     
        }
    }
    
}
