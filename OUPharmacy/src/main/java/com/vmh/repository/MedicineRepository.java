/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository;

import com.vmh.pojo.Medicine;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface MedicineRepository {
   List<Medicine> getMedicines();
   Medicine getMedicineDetail(int id);
   boolean getMedicineByName(String name);
   boolean addMedicine(Medicine medicine);
   boolean getMedicineNameUnique(String medicineName);
}
