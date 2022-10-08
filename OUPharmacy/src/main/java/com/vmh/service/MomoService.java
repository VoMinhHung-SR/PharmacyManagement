/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.Bill;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public interface MomoService {
    JSONObject payment(long total, int presciptionId);
}
