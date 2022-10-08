/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.vmh.pojo.OnCallSchedule;
import com.vmh.repository.OnCallScheduleRepository;
import com.vmh.service.OnCallScheduleService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service 
public class OnCallScheduleServiceImpl implements OnCallScheduleService{

    @Autowired
    private OnCallScheduleRepository onCallScheduleRepository;
    
    
    @Override
    public boolean addCalender(OnCallSchedule o) {
       return this.onCallScheduleRepository.addCalender(o);
    }

    @Override
    public List<OnCallSchedule> getSchedule(Date d) {
       return this.onCallScheduleRepository.getSchedule(d);
    }

}
