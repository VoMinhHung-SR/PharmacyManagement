/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository;

import com.vmh.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface UserRepository {
    boolean checkPassword(int userId, String password);
    boolean getUniqueUserName(String username);
    boolean getUnitqueEmail(String email);
    boolean addUser(User user);
    boolean addUserWithUserRole(User user, String userRole);
    boolean editAdminUser(int userId);
    boolean setActiveUser(int userId);
    boolean updateUser(User u, int userId);
    User getUserById(int id);
    List<User> getUser(String username);
    List<User> getUserNotAdmin(Map<String, String> params);
    List<User> getUserByUserRole(Map<String, String> params,String userRole, int page);
    List<User> getUserByMultipleRole(Map<String, String> params);
    int countUsersByUserRole(String userRole);
    int countUser();
    int countUserWithoutAdmin();
}
