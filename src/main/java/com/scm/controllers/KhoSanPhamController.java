/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.KhoSanpham;
import com.scm.services.QuanLyKhoService;
import com.scm.services.SanPhamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/admin")
public class KhoSanPhamController {
    @Autowired
    private QuanLyKhoService qlkService;
    
    private SanPhamService spService;
    
    @GetMapping("/kho/{id}")
    public String getWarehouseDetail(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("sanpham", this.qlkService.getSanPhamTrongKho(id));
        
        return "admin/detail_kho";
    }
}
