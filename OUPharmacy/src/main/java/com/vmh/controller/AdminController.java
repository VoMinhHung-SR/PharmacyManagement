/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.service.AdminStatsService;
import com.vmh.service.MedicineUnitService;
import com.vmh.service.PatientService;
import com.vmh.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired
    private UserService userService;

    @Autowired
    private MedicineUnitService medicineUnitService;

    @Autowired
    private PatientService patientService;

    @ModelAttribute
    public void commonStats(Model model) {
        model.addAttribute("countUser", this.userService.countUser());
        model.addAttribute("countMedicineUnit", this.medicineUnitService.countMedicines());
        model.addAttribute("countPatient", this.patientService.countPatient());
    }

    @GetMapping("/dashboard")
    public String dashboardView(Model model,
            @RequestParam(name = "kw", defaultValue = "") String kw) {
        model.addAttribute("patientStats", this.adminStatsService.getPatientStats());
        model.addAttribute("medicineDateStats", this.adminStatsService.getMedicineFrequencyStats(kw));
        return "dashboard";
    }

    @GetMapping("/patient-stats")
    public String patientStatsView(Model model,
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
        model.addAttribute("option", 1);
        model.addAttribute("patientStats", this.adminStatsService.getPatientStats());
        model.addAttribute("patientDateStats", this.adminStatsService.getPatientDateStats(kw, fd, td));
        return "option-stats";
    }

    @GetMapping("/revenue-stats")
    public String revenueStatsView(Model model,
            @RequestParam(name = "year", defaultValue = "0") String yearStr,
            @RequestParam(required = false) Map<String, String> params) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        
        int month, year, quater;

        month = Integer.parseInt(params.getOrDefault("month", "0"));
        quater = Integer.parseInt(params.getOrDefault("quater", "0"));
        year = Integer.parseInt(yearStr);
        model.addAttribute("option", 2);
        model.addAttribute("revenueDateStats", 
                this.adminStatsService.getRevenueByMonthOption(month, quater, year));
        return "option-stats";
    }
}
