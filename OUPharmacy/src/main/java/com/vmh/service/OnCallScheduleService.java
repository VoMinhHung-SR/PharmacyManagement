/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.OnCallSchedule;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface OnCallScheduleService {
    boolean addCalender (OnCallSchedule o);
    List<OnCallSchedule> getSchedule (Date d);
}
