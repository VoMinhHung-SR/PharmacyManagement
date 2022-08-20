/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.pojo.User;
import com.vmh.service.ExaminationService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author ASUS
 */
@Controller
public class ExaminationDetailController {
    @Autowired
    private ExaminationService examinationService;
    
    @GetMapping(path="/booking/{bookingId}/examination-detail")
    public String getExamination(Model model,
            @PathVariable(value="bookingId") int bookingId,
            HttpSession session){
        User u = (User) session.getAttribute("currentUser");
        if(u != null){
            try{
                model.addAttribute("bookingList", 
                        this.examinationService.getExaminations());
                model.addAttribute("examination", this.examinationService.getExaminationById(bookingId));
                return "examination-detail";
            }catch(Exception ex){
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        model.addAttribute("errMgs", "Da co loi xay ra, vui long quay lai sau!!");      
        return "examination-detail";
    }
}
