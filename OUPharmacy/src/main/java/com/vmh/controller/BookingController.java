/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.pojo.Examination;
import com.vmh.pojo.User;
import com.vmh.service.ExaminationService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
public class BookingController {
    
    @Autowired
    private ExaminationService examinationService;
    
    @GetMapping("/booking")
    public String bookingView(Model model){
        model.addAttribute(new Examination());
        return "booking";
    }
    @GetMapping("/booking-list")
    public String bookingListView(Model model, @RequestParam Map<String, String> params, HttpSession session){
        User u = (User) session.getAttribute("currentUser");
        if(u != null){
            int userId = u.getId();
            try{
                model.addAttribute("bookingList", 
                        this.examinationService.getExaminationByUserId( params, userId));
                return "booking-list";
            }catch(Exception ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        model.addAttribute("errMgs", "Da co loi xay ra, vui long quay lai sau!!");      
        return "booking-list";
    }
}
