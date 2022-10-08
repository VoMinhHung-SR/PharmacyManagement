/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.controller;

import com.vmh.pojo.Bill;
import com.vmh.pojo.Prescription;
import com.vmh.pojo.User;
import com.vmh.service.BillService;
import com.vmh.service.MomoService;
import com.vmh.service.PrescriptionService;
import java.util.Date;
import java.util.HashMap;
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
public class MomoController {
    @Autowired
    private MomoService momoService;
    
    @Autowired
    private BillService billService;
    
    @Autowired
    private PrescriptionService prescriptionService;
    
    @GetMapping("/momo-payment")
    public String momoPaymentView(Model model, HttpSession session, 
            @RequestParam Map<String, String> params){
        int resultCode = Integer.parseInt(params.get("resultCode"));
        Map<String, String> momoSession = (Map<String, String>) session.getAttribute("momoSession");
    
        if (resultCode == 0
                && params.get("partnerCode").equals(momoSession.get("partnerCode"))
                && params.get("requestId").equals(momoSession.get("requestId"))
                && params.get("orderId").equals(momoSession.get("orderId"))
                && params.get("amount").equals(momoSession.get("amount"))) {
            try {
                double amount = Double.parseDouble(params.get("amount"));
                int prescriptionId = Integer.parseInt(momoSession.get("prescriptionId"));
                
                Prescription p = this.prescriptionService.getPrescriptionById(prescriptionId);
                
//                User u = (User) session.getAttribute("currentUser");
                Bill bill = new Bill();
                bill.setPay(amount);
                bill.setPrescriptionBillId(p);
                bill.setCreatedDate(new Date());
                
                Bill b = this.billService.addBill(bill);
                
                if (b != null) {
                    session.removeAttribute("momoSession");
                    model.addAttribute("message", "thanh toan thanh cong");
//                    return "redirect:/";
                    return "momo-payment";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        session.removeAttribute("momoSession");
        model.addAttribute("message", params.get("message"));
        model.addAttribute("errMsgs", "Thanh toán MoMo đã xảy ra lỗi!! Vui lòng chuyển đổi hình thức thanh toán khác.");
        return "momo-payment";
    }
    
}
