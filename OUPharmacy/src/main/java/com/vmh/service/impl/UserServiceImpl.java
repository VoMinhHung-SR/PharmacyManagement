/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vmh.pojo.User;
import com.vmh.repository.UserRepository;
import com.vmh.service.CloudinaryService;
import com.vmh.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private Cloudinary cloudinary;
    // Custome
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @Override
    public boolean addUser(User user) {
        try {
            String pass = user.getPassword();
            user.setPassword(this.passwordEncoder.encode(pass));
            user.setUserRole(User.PATIENT);
            
            Map r = this.cloudinary.uploader().upload(user.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            
            user.setAvatar((String) r.get("secure_url"));
            this.userRepository.addUser(user);
            return true;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getUser(String username) {
        return this.userRepository.getUser(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.getUser(username);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }
        User u = users.get(0);
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public List<User> getUserByUserRole(Map<String, String> params, String string, int page) {
        return this.userRepository.getUserByUserRole(params,string,page);
    }

    @Override
    public boolean addUserWithUserRole(User user, String string) {
        try {
            String pass = user.getPassword();
            user.setPassword(this.passwordEncoder.encode(pass));
            user.setUserRole(string);
            
            Map r = this.cloudinary.uploader().upload(user.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            
            user.setAvatar((String) r.get("secure_url"));
            this.userRepository.addUserWithUserRole(user,string);
            return true;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public int countUsersByUserRole(String string) {
        return this.userRepository.countUsersByUserRole(string);
    }

    @Override
    public int countUser() {
        return this.userRepository.countUser();
    }

    @Override
    public List<User> getUserNotAdmin(Map<String, String> map) {
        return this.userRepository.getUserNotAdmin(map);
    }

    @Override
    public int countUserWithoutAdmin() {
        return this.userRepository.countUserWithoutAdmin();
    }

    @Override
    public boolean editAdminUser(int i) {
        return this.userRepository.editAdminUser(i);
    }

    @Override
    public User getUserById(int i) {
        return this.userRepository.getUserById(i);
    }

    @Override
    public boolean getUniqueUserName(String string) {
        return this.userRepository.getUniqueUserName(string);
    }

    @Override
    public boolean getUnitqueEmail(String string) {
        return this.userRepository.getUnitqueEmail(string);
    }

    @Override
    public boolean setActiveUser(int i) {
        return this.userRepository.setActiveUser(i);
    }

    @Override
    public boolean updateUser(User u, int userId, MultipartFile file) {
        if(file != null){
            String avatarStr = this.cloudinaryService.uploadAvatar(file);
            u.setAvatar(avatarStr);
        }
        return this.userRepository.updateUser(u, userId);
    }

}
