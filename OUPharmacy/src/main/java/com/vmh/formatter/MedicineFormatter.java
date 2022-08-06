/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.formatter;

import com.vmh.pojo.Medicine;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ASUS
 */
public class MedicineFormatter implements Formatter<Medicine>{

    @Override
    public String print(Medicine t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Medicine parse(String string, Locale locale) throws ParseException {
        Medicine m = new Medicine();
        m.setId(Integer.parseInt(string));
        return m;
    }
    
}
