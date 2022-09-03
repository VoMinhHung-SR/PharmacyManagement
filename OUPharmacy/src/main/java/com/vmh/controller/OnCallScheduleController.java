/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.service.OnCallScheduleService;
import java.util.Date;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@Controller
public class OnCallScheduleController {

    @Autowired
    private OnCallScheduleService onCallScheduleService;

    @GetMapping("/admin/add-schedule")
    public String addScheduleView(Model model) {

        Date date = new Date();
        try {
            if (this.onCallScheduleService.getSchedule(date) != null) {
                model.addAttribute("todaySchedule", this.onCallScheduleService.getSchedule(date));
            }
        } catch (NoResultException n) {
            model.addAttribute("todaySchedule", null);
        }
        return "add-schedule";

    }
}
