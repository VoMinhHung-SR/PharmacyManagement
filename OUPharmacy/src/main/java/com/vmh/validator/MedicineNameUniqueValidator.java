/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.validator;


import com.vmh.pojo.Medicine;
import com.vmh.service.MedicineService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author ASUS
 */
public class MedicineNameUniqueValidator implements Validator{
     private final MedicineService medicineService;
    
    public MedicineNameUniqueValidator(MedicineService medicineService){
        this.medicineService = medicineService;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Medicine.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Medicine m = (Medicine) target;
              
        if(this.medicineService.getMedicineNameUnique(m.getName())){
            errors.rejectValue("name", 
                    "medicine.name.uniqueErr",
                    "Tên thuốc đã tồn tại");
           
        }
    }
}
