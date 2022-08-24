/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.pojo.MedicineUnit;
import com.vmh.service.MedicineService;
import com.vmh.service.MedicineUnitService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ASUS
 */
@Controller
public class MedicineUnitController {

    @Autowired
    private MedicineUnitService medicineUnitService;

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/admin/medicines/add-medicine-unit")
    public String addMedicineUnitView(Model model) {
        model.addAttribute("medicineUnit", new MedicineUnit());
        model.addAttribute("medicines", this.medicineService.getMedicines());
        return "add-medicine-unit";
    }

    @PostMapping("/admin/medicines/add-medicine-unit")
    public String addMedicineUnit(Model model,
            @ModelAttribute(value = "medicineUnit") MedicineUnit medicineUnit) {
        if (this.medicineUnitService.addMedicineUnit(medicineUnit)) {
            return "redirect:/admin/medicines";
        } else {
            model.addAttribute("errMsg", "Loi Upload");
        }
        return "add-medicine-unit";
    }
}
