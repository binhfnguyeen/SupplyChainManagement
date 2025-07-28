/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Khachhang;
import com.scm.pojo.User;
import com.scm.services.KhachHangService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/admin")
public class KhachHangController {
    @Autowired
    private KhachHangService khService;
    
    @RequestMapping("/customer")
    public String dsKhachHang(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("khachhang", this.khService.getDsKhachHang(params));
        return "admin/khachhang";
    }
    
    @DeleteMapping("/customer/{cusId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable(value = "cusId") int id) {
        this.khService.deleteKhachHang(id);
    }
    
    @GetMapping("/customer/form")
    public String addOrUpdateNhanVien(Model model) {
        Khachhang kh = new Khachhang();
        kh.setUserID(new User());

        model.addAttribute("khachhang", kh);

        return "admin/form_kh";
    }
    
    @PostMapping("/customer/form")
    public String addOrUpdateNhanvienWithUser(@ModelAttribute("khachhang") Khachhang kh) {
        this.khService.addOrUpdateKhachHangWithUser(kh);
        return "redirect:/admin/customer";
    }
    
    @GetMapping("/customer/update/{id}")
    public String updateNhanVien(Model model, @PathVariable(value = "id") int id) {
        Khachhang kh = this.khService.getKhachHangById(id);
        model.addAttribute("khachhang", kh);
        return "admin/form_kh";
    }
}
