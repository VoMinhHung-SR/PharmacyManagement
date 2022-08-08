/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.validator;

import com.vmh.pojo.MedicineUnit;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author ASUS
 */
public class MedicineUnitValidator implements Validator{

     @Override
    public boolean supports(Class<?> clazz) {
        return MedicineUnit.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MedicineUnit medicineUnit = (MedicineUnit) target;
    }
    
}
