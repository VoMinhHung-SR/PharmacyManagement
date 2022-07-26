/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vmh.pojo.MedicineUnit;
import com.vmh.repository.MedicineUnitRepository;
import com.vmh.service.CloudinaryService;
import com.vmh.service.MedicineUnitService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Service
public class MedicineUnitServiceImpl implements MedicineUnitService{

    @Autowired
    private MedicineUnitRepository medicineUnitRepository;
    // Custome
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Override
    public List<MedicineUnit> getMedicineUnits(Map<String, String> params, int page) {
        return this.medicineUnitRepository.getMedicineUnits(params, page);
    }

    @Override
    public MedicineUnit getMedicineUnitDetail(int id) {
        return this.medicineUnitRepository.getMedicineUnitDetail(id);
    }

    @Override
    public boolean addMedicineUnit(MedicineUnit medicineUnit) {
        try{
           Map r = this.cloudinary.uploader().upload(medicineUnit.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            medicineUnit.setImage((String) r.get("secure_url"));
            this.medicineUnitRepository.addMedicineUnit(medicineUnit);
            return true;
            
        }catch(IOException ex){
            System.err.print(ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteMedicine(int id) {
        return this.medicineUnitRepository.deleteMedicines(id);
    }

    @Override
    public int countMedicines() {
        return this.medicineUnitRepository.countMedicines();
    }

    @Override
    public boolean updateMedicineUnit(MedicineUnit mu, int i, MultipartFile file) {
        if(file != null){
            String img = this.cloudinaryService.uploadAvatar(file);
            mu.setImage(img);
        }
        return this.medicineUnitRepository.updateMedicineUnit(mu, i);
    }
    
}
