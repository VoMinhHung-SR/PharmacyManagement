/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository;

import com.vmh.pojo.OnCallSchedule;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface OnCallScheduleRepository {
    List<OnCallSchedule> getSchedule (Date d);
    boolean addCalender (OnCallSchedule o);
}
