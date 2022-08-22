/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.service.AdminStatsService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminStatsService adminStatsService;

    @GetMapping("/dashboard")
    public String dashboardView(Model model) {
        model.addAttribute("patientStats", this.adminStatsService.getPatientStats());
        return "dashboard";
    }

    @GetMapping("/patient-stats")
    public String patientStatsView(Model model) {
        model.addAttribute("option", 1);
        model.addAttribute("patientStats", this.adminStatsService.getPatientStats());
        model.addAttribute("patientDateStats", this.adminStatsService.getPatientDateStats(null, null, null));
        return "patient-stats";
    }

    @GetMapping("/revenue-stats")
    public String revenueStatsView(Model model, 
            @RequestParam(name = "kw", defaultValue = "") String kw,
            @RequestParam(name = "fromDate", defaultValue = "") String fromDate,
            @RequestParam(name = "toDate", defaultValue = "") String toDate) {
        Date fd = null, td = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (fromDate != null && !fromDate.isEmpty()) {
                fd = f.parse(fromDate);
            }
            if (toDate != null && !toDate.isEmpty()) {
                td = f.parse(toDate);
            }
        } catch (ParseException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        model.addAttribute("option", 2);
        model.addAttribute("revenueDateStats", this.adminStatsService.getRevenueByMonth(fd, td));
        return "patient-stats";
    }
}
