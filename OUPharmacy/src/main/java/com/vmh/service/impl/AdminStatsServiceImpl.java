/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service.impl;

import com.vmh.repository.AdminStatsReposioty;
import com.vmh.service.AdminStatsService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class AdminStatsServiceImpl implements AdminStatsService{

    @Autowired
    private AdminStatsReposioty adminStatsReposioty;
    
    
    @Override
    public List<Object[]> getPatientStats() {
        return this.adminStatsReposioty.getPatientStats();
    }

    @Override
    public List<Object[]> getPatientDateStats(String kw, Date fromDate, Date toDate) {
        return this.adminStatsReposioty.getPatientDateStats(kw, fromDate, toDate);
    }

    @Override
    public List<Object[]> getRevenueByMonth(Date fromDate, Date toDate) {
        return this.adminStatsReposioty.getRevenueByMonth(toDate, toDate);
    }

    @Override
    public List<Object[]> getMedicineFrequencyStats(String string) {
        return this.adminStatsReposioty.getMedicineFrequencyStats(string);
    }
    
}
