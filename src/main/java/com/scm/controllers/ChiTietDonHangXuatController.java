/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Chitietdonhangxuat;
import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.services.ChiTietDonHangXuatService;
import com.scm.services.DonHangXuatService;
import com.scm.services.NhaCungCapService;
import com.scm.services.SanPhamService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/admin")
public class ChiTietDonHangXuatController {
    @Autowired
    private ChiTietDonHangXuatService chitietService;
    
    @Autowired
    private DonHangXuatService dhxService;
    
    @Autowired
    private SanPhamService spService;
    
    
    @Autowired
    private NhaCungCapService nccService;
    
    @ModelAttribute("dsNhaCungCap")
    public List<Nhacungcap> getNhacungcap() {
        return this.nccService.getDsNhaCungCap(null);
    }
    
    
    @ModelAttribute("dsDonHangXuat")
    public List<Donhangxuat> getNhanVien() {
        return this.dhxService.getDonhangxuat(null);
    }
    
    @ModelAttribute("dsSanPham")
    public List<Sanpham> getSanPham() {
        return this.spService.getAllSanpham(null);
    }
    
    @RequestMapping("/ds-chitiet-dhx")
    public String list(Model model) {
        model.addAttribute("chitiet", this.chitietService.getAllChiTiet());
        return "admin/chitiet-dhx";
    }

    @GetMapping("/ds-chitiet-dhx/form")
    public String addOrUpdateChiTiet(Model model) {
        Chitietdonhangxuat chitiet = new Chitietdonhangxuat();
        model.addAttribute("chitiet", chitiet);
        return "admin/form_chitiet_dhx";
    }

    @PostMapping("/ds-chitiet-dhx/form")
    public String addOrUpdateChiTiet(@ModelAttribute("chitiet") Chitietdonhangxuat chitiet, RedirectAttributes redirectAttrs) {
        this.chitietService.addOrUpdateChiTiet(chitiet);
        return "redirect:/admin/ds-chitiet-dhx";
    }

    @GetMapping("/ds-chitiet-dhx/update/{id}")
    public String updateChiTiet(Model model, @PathVariable(value = "id") int id) {
        Chitietdonhangxuat chitiet = this.chitietService.getChiTietById(id);
        model.addAttribute("chitiet", chitiet);
        return "admin/form_chitiet_dhx";
    }

    @DeleteMapping("/ds-chitiet-dhx/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChiTiet(@PathVariable(value = "id") int id) {
        this.chitietService.deleteChiTiet(id);
    }
}
