/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.services.KhachHangService;
import com.scm.services.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Dell
 */
@Controller
@RequestMapping("/admin")
public class HomeController {
    @Autowired
    private KhachHangService khService;
    
    @Autowired
    private NhanVienService nvService;
    
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("soKH", this.khService.soKhachHang());
        model.addAttribute("soNV", this.nvService.soNhanVien());
        return "admin/index";
    }
}
