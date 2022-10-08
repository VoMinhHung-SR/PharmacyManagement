/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;


import com.vmh.pojo.Bill;
import com.vmh.service.MomoService;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class MomoServiceImpl implements MomoService{

    private String hashCode(String secretKey, String rawSignature){
        Mac hasher;
        try {
            byte[] signa = null;
            hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(secretKey.getBytes(), "HmacSHA256"));
            signa = hasher.doFinal(rawSignature.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(signa.length * 2);
            Formatter formatter = new Formatter(sb);
            for (byte b : signa) {
                formatter.format("%02x", b);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MomoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(MomoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public JSONObject payment(long total, int prescriptionId) {
        Random rand = new Random();
        String requestID = hashCode("requestID", String.valueOf(rand.nextFloat()));
        String orderID = hashCode("orderID", String.valueOf(rand.nextFloat()));
        
        String endpoint = "https://test-payment.momo.vn/v2/gateway/api/create";
        String partnerCode = "MOMOPZQO20220908";
        String accessKey = "YCyiVT9bM5fS3W72";
        String secretKey = "v2srvmKzz6f5wVht5OwcXWErUhBdn4tq";
        String orderInfo = "Pay the order";
        String redirectUrl = "http://localhost:8080/OUPharmacy/momo-payment";
        String ipnUrl = "http://localhost:8080/OUPharmacy/momo-payment";
        
        String amount = String.valueOf(total);
        String orderId = String.valueOf(orderID);
        String requestId = String.valueOf(requestID);
        String prescriptionBillId = String.valueOf(prescriptionId);
        
        String requestType = "captureWallet";
        String extraData = "";
        String rawSignature = "accessKey=" + accessKey + "&amount=" + amount + "&extraData=" + extraData + "&ipnUrl=" + ipnUrl + "&orderId=" + orderId + "&orderInfo=" + orderInfo + "&partnerCode=" + partnerCode + "&redirectUrl=" + redirectUrl + "&requestId=" + requestId + "&requestType=" + requestType;
        String signature = hashCode(secretKey, rawSignature);
        
        JSONObject data = new JSONObject();
        data.put("partnerCode", partnerCode);
        data.put("partnerName", "Test");
        data.put("storeId", "MomoTestStore");
        data.put("requestId", requestId);
        data.put("amount", amount);
        data.put("orderId", orderId);
        data.put("orderInfo", orderInfo);
        data.put("redirectUrl", redirectUrl);
        data.put("ipnUrl", ipnUrl);
        data.put("lang", "vi");
        data.put("extraData", extraData);
        data.put("requestType", requestType);
        data.put("signature", signature);
        data.put("prescriptionId", prescriptionBillId);
        
//        for (Cart c : cart.values()) {
//            JSONObject item = new JSONObject();
//            item.put("id", c.getMenuId());
//            item.put("name", c.getMenuName());
//            item.put("imageUrl", c.getImage());
//            item.put("price", c.getPrice());
//            item.put("quantity", c.getQuantity());
//            item.put("totalPrice", c.getPrice() + c.getQuantity());
//            data.append("items", item);
//        }
        String clen = String.valueOf(data.length());
        try {
            URL url = new URL(endpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Length", clen);
            con.setDoOutput(true);
            try ( OutputStream os = con.getOutputStream()) {
                byte[] input = data.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try ( BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                String responseLine = null;
                
                StringBuilder response = new StringBuilder();
                
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                
                JSONObject res = new JSONObject(response.toString());
                res.put("momoSession", data);
                con.disconnect();         
                return res; //Key:momoSession; value = data {...}
            }
        } catch (ProtocolException ex) {
            Logger.getLogger(MomoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MomoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MomoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
