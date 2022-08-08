/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ASUS
 */
@Controller
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/admin/categories")
    public String cateView(Model model){
        model.addAttribute("cateCounter", this.categoryService.countCategories());
        return "categories";
    }
}
