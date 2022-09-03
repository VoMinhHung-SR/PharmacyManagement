/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.OnCallSchedule;
import com.vmh.pojo.User;
import com.vmh.service.OnCallScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class ApiOnCallScheduleController {

    @Autowired
    private OnCallScheduleService onCallScheduleService;

    @PostMapping(path = "/add-schedule", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OnCallSchedule> addSchedule(@RequestBody OnCallSchedule o) {
        try {
            if (this.onCallScheduleService.addCalender(o)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
