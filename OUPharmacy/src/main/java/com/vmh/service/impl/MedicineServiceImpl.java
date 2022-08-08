/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.vmh.pojo.Medicine;
import com.vmh.repository.MedicineRepository;
import com.vmh.service.MedicineService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class MedicineServiceImpl implements MedicineService{
    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public boolean getMedicineByName(String name) {
        return this.medicineRepository.getMedicineByName(name);
    }

    @Override
    public List<Medicine> getMedicines() {
        return this.medicineRepository.getMedicines();
    }

    @Override
    public boolean addMedicine(Medicine mdcn) {
         return this.medicineRepository.addMedicine(mdcn);
    }

    @Override
    public Medicine getMedicineDetail(int i) {
        return this.medicineRepository.getMedicineDetail(i);
    }
}
