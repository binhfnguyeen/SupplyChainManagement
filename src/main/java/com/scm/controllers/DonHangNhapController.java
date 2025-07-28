/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Donhangnhap;
import com.scm.pojo.Kho;
import com.scm.pojo.Nhanvien;
import com.scm.pojo.Vanchuyen;
import com.scm.services.DonHangNhapService;
import com.scm.services.KhoService;
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
 * @author Dell
 */
@Controller
@RequestMapping("/admin")
public class DonHangNhapController {

    @Autowired
    private DonHangNhapService dhnService;
    
    @Autowired
    private NhanVienService nvService;
    
    @Autowired
    private KhoService khoService;
    
    @Autowired
    private VanChuyenService vcService;
    
    @ModelAttribute("dsNhanVien")
    public List<Nhanvien> getNhanVien() {
        return this.nvService.getDsNhanVien(null);
    }
    
    @ModelAttribute("dsKho")
    public List<Kho> getKho() {
        return this.khoService.getAllKho(null);
    }
    
    @ModelAttribute("dsVanChuyen")
    public List<Vanchuyen> getVanChuyen() {
        return this.vcService.getAllVanChuyen(null);
    }

    @RequestMapping("/ds-donhangnhap")
    public String list(Model model) {
        model.addAttribute("donhangnhap", this.dhnService.getAllDonHangNhap());
        return "admin/donhangnhap";
    }

    @GetMapping("/ds-donhangnhap/form")
    public String addOrUpdateDonHangNhap(Model model) {
        Donhangnhap dhn = new Donhangnhap();
        model.addAttribute("donhangnhap", dhn);
        return "admin/form_dhn";
    }

    @PostMapping("/ds-donhangnhap/form")
    public String addOrUpdateVanChuyen(@ModelAttribute("donhangnhap") Donhangnhap dhn) {
        this.dhnService.addOrUpdateDonHangNhap(dhn);
        return "redirect:/admin/ds-donhangnhap";
    }

    @GetMapping("/ds-donhangnhap/update/{id}")
    public String updateDonHangNhap(Model model, @PathVariable(value = "id") int id) {
        Donhangnhap dhn = this.dhnService.getDonHangNhapById(id);
        model.addAttribute("donhangnhap", dhn);
        return "admin/form_dhn";
    }

    @DeleteMapping("/ds-donhangnhap/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDonHangNhap(@PathVariable(value = "id") int id) {
        this.dhnService.deleteDonHangNhap(id);
    }
}
