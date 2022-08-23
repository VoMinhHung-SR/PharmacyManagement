/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.repository;

import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface AdminStatsReposioty {
   List<Object[]> getPatientStats();
   List<Object[]> getPatientDateStats(String kw, Date fromDate, Date toDate);
   List<Object[]> getRevenueByMonth(Date fromDate, Date toDate);
   List<Object[]> getMedicineFrequencyStats(String kw);
}
