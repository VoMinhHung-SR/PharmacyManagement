/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository;

import com.vmh.pojo.User;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface UserRepository {
    boolean addUser(User user);
    List<User> getUser(String username);
}
