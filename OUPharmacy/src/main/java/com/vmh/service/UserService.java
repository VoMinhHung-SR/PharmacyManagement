/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author ASUS
 */
public interface UserService extends UserDetailsService{
   boolean addUser(User user); 
   boolean addUserWithUserRole(User user, String userRole);
   List<User> getUser(String username);
   List<User> getUserByUserRole(String userRole);
}
