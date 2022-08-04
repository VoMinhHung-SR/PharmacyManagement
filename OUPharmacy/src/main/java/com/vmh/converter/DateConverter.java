/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author ASUS
 */
public class DateConverter implements Converter<String, Date>{
    
    private final String datePattern;
    
    public DateConverter(String datePattern){ this.datePattern = datePattern;}
    
    @Override
    public Date convert(String s){
        if(!s.isEmpty()){
            SimpleDateFormat f = new SimpleDateFormat(this.datePattern);
            try{
                return f.parse(s);
            }catch(ParseException ex){
                throw new RuntimeException(ex);
            }
        }
        return null;
    }
}
