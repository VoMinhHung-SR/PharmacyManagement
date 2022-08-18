/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.Bill;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface BillService {
    Bill addBill (Bill bill);
    List<Bill> getBillByPrescriptionId(Map<String, String> params, int prescriptionId);
}
