/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Khachhang;
import com.scm.pojo.Nhanvien;
import com.scm.pojo.Vanchuyen;
import com.scm.services.DonHangXuatService;
import com.scm.services.KhachHangService;
import com.scm.services.NhanVienService;
import com.scm.services.VanChuyenService;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/admin")
public class DonHangXuatController {
    @Autowired
    private DonHangXuatService dhxService;
    
    @Autowired
    private KhachHangService khService;
    
    @Autowired
    private NhanVienService nvService;
    
    @Autowired
    private VanChuyenService vcService;
    
    @ModelAttribute("dsNhanVien")
    public List<Nhanvien> getNhanVien() {
        return this.nvService.getDsNhanVien(null);
    }
    
    @ModelAttribute("dsKhachHang")
    public List<Khachhang> getKhachHang() {
        return this.khService.getDsKhachHang(null);
    }
    
    @ModelAttribute("dsVanChuyen")
    public List<Vanchuyen> getVanChuyen() {
        return this.vcService.getAllVanChuyen(null);
    }
    
    @RequestMapping("/ds-donhangxuat")
    public String list(Model model) {
        model.addAttribute("donhangxuat", this.dhxService.getDonhangxuat(null));
        return "admin/donhangxuat";
    }
    
    @GetMapping("/ds-donhangxuat/form")
    public String addOrUpdateDonHangXuat(Model model) {
        Donhangxuat dhx = new Donhangxuat();
        model.addAttribute("donhangxuat", dhx);
        return "admin/form_dhx";
    }
    
     @PostMapping("/ds-donhangxuat/form")
    public String addOrUpdateVanChuyen(@ModelAttribute("donhangxuat") Donhangxuat dhx) {
        this.dhxService.UpdateDonhangxuat(dhx);
        return "redirect:/admin/ds-donhangxuat";
    }
    
    @GetMapping("/ds-donhangxuat/update/{id}")
    public String updateDonHangXuat(Model model, @PathVariable(value = "id") int id) {
        Donhangxuat dhx = this.dhxService.getDonhangxuatById(id);
        model.addAttribute("donhangxuat", dhx);
        return "admin/form_dhx";
    }
    
    @DeleteMapping("/ds-donhangxuat/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDonHangNhap(@PathVariable(value = "id") int id) {
        this.dhxService.deleteDonhangxuat(id);
    }
}
