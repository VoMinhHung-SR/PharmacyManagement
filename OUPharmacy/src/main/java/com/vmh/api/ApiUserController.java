/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.User;
import com.vmh.service.UserService;
import com.vmh.validator.WebAppValidator;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UserService userDetailService;

    @Autowired
    private WebAppValidator userValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }

    @PostMapping(path = "/users/{userId}/edit-user-role")
    public ResponseEntity<User> editUserRole(@PathVariable(value = "userId") int userId) {
        try {
            if (this.userDetailService.editAdminUser(userId)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/users/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> getUserDetail(@PathVariable(value = "userId") int userId) {
        try {
            return new ResponseEntity<>(this.userDetailService.getUserById(userId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(path = "/users/{userId}/update-active", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> updateActiveUser(@PathVariable(value = "userId") int userId) {
        try {
            if (this.userDetailService.setActiveUser(userId)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/users/{userId}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable("userId") int userId,
            @RequestPart(value = "avatarFile", required = false) MultipartFile file,
            @RequestPart("user") @Valid User u,
            BindingResult result) throws IOException {

        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;

//        System.out.println(u.getUsername());
//        System.out.println(u.getPassword());
//        System.out.println(u.getGender());
//        System.out.println(u.getEmail());
//        System.out.println(u.getFirstName());
//        System.out.println(u.getLastName());
//        System.out.println(u.getAddress());
//        System.out.println(u.getDateOfBirth());

        
        if (!result.hasErrors()) {
            if (this.userDetailService.updateUser(u, userId, file)) {
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new ResponseEntity<>(errorMessages, status);
        }

        List<FieldError> errors = result.getFieldErrors();
        for (FieldError error : errors) {
            errorMessages.put(error.getField(), error.getDefaultMessage());
        }
        status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(errorMessages, status);
    }

}
