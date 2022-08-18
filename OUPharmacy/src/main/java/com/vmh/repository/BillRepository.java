/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository;

import com.vmh.pojo.Bill;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface BillRepository {
    List<Bill> getBillByPrescriptionId(Map<String, String> params, int prescriptionId);
    Bill addBill (Bill bill);
}
