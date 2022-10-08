/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.service.MomoService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ApiMomoPaymentController {
    @Autowired
    private MomoService momoService;
    
    
    @PostMapping("/momo-payment")
    public ResponseEntity<Map<String, String>> MomoPayment(HttpSession session, 
            @RequestBody Map<String, String> params) {
        
        long total = Long.parseLong(params.get("total"));
        int prescriptionId = Integer.parseInt(params.get("prescriptionId"));
                
        Map<String, String> result = new HashMap<>();
        Map<String, String> momoSession = new HashMap<>();
        //Response Data {}       
        JSONObject data = this.momoService.payment(total, prescriptionId);
        result.put("payUrl", data.get("payUrl").toString());
        Iterator<String> temp = data.getJSONObject("momoSession").keys();
        while (temp.hasNext()) {
            String key = temp.next();
            momoSession.put(key, data.getJSONObject("momoSession").get(key).toString());
        }
        session.setAttribute("momoSession", momoSession);
        if (data != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        
    }
}
