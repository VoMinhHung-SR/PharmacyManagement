/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.MedicineUnit;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface MedicineUnitService {
    List<MedicineUnit> getMedicineUnits(Map<String, String> params, int page);
    MedicineUnit getMedicineUnitDetail(int id);
    boolean addMedicineUnit (MedicineUnit medicineUnit);
    boolean updateMedicineUnit (MedicineUnit medicineUnit, int medicineUnitId);
    boolean deleteMedicine(int id);
    int countMedicines();
}
