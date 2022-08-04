/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.service;

import com.vmh.pojo.Category;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface CategoryService {
    List<Category> getCategories();
    Category getCategoryById(int cateId);
}
