/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.pojo.Medicine;
import com.vmh.pojo.MedicineUnit;
import com.vmh.service.MedicineService;
import com.vmh.service.MedicineUnitService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
public class MedicineController {
    // ==== Admin ====

    @Autowired
    private MedicineUnitService medicineUnitService;

    @Autowired
    MedicineService medicineService;

    @GetMapping("/medicines")
    public String userListMedicineView(Model model, 
            @RequestParam(required = false) Map<String, String> params,
            @RequestParam(required = false, defaultValue = "1") String page){
        int p = Integer.parseInt(page);
        model.addAttribute("medicines", this.medicineUnitService.getMedicineUnits(params, p));
        model.addAttribute("medicinesCouter", this.medicineUnitService.countMedicines());
        return "user-medicines";
    }

    
    
    @GetMapping("/admin/medicines")
    public String listMedicinesView(Model model, 
            @RequestParam(required = false) Map<String, String> params,
            @RequestParam(required = false, defaultValue = "1") String page) {
        int p = Integer.parseInt(page);
        model.addAttribute("medicines", this.medicineUnitService.getMedicineUnits(params, p));
        model.addAttribute("medicinesCouter", this.medicineUnitService.countMedicines());
        return "medicines";
    }

    @GetMapping("/admin/medicines/add-medicine")
    public String addMedicineView(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "add-medicine";
    }

    @PostMapping("/admin/medicines/add-medicine")
    public String addMedicine(Model model, @ModelAttribute(value = "medicine") Medicine medicine) {
        if (this.medicineService.addMedicine(medicine)) {
            return "redirect:/admin/medicines/add-medicine-unit";
        } else {
            model.addAttribute("errMsg", "Loi Upload");
        }
        return "add-medicine";
    }

    @GetMapping("/admin/medicines/add-medicine-unit")
    public String addMedicineUnitView(Model model) {
        model.addAttribute("medicineUnit", new MedicineUnit());
        model.addAttribute("medicines", this.medicineService.getMedicines());
        return "add-medicine-unit";
    }

    @PostMapping("/admin/medicines/add-medicine-unit")
    public String addMedicineUnit(Model model, @ModelAttribute(value = "medicineUnit") MedicineUnit medicineUnit) {

        if (this.medicineUnitService.addMedicineUnit(medicineUnit)) {
            return "redirect:/admin/medicines";
        } else {
            model.addAttribute("errMsg", "Loi Upload");
        }

        return "add-medicine-unit";
    }

}
