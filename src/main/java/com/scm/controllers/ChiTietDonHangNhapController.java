/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Chitietdonhangnhap;
import com.scm.pojo.Donhangnhap;
import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.services.ChiTietDonHangNhapService;
import com.scm.services.DonHangNhapService;
import com.scm.services.NhaCungCapService;
import com.scm.services.SanPhamNhaCungCapService;
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

/**
 *
 * @author Dell
 */
@Controller
@RequestMapping("/admin")
public class ChiTietDonHangNhapController {

    @Autowired
    private ChiTietDonHangNhapService chitietService;

    @Autowired
    private DonHangNhapService dhnService;

    @Autowired
    private NhaCungCapService nccService;

    @ModelAttribute("dsDonHangNhap")
    public List<Donhangnhap> getNhanVien() {
        return this.dhnService.getAllDonHangNhap();
    }

    @ModelAttribute("dsNhaCungCap")
    public List<Nhacungcap> getNhacungcap() {
        return this.nccService.getDsNhaCungCap(null);
    }

    @RequestMapping("/ds-chitiet-dhn")
    public String list(Model model) {
        model.addAttribute("chitiet", this.chitietService.getAllChiTiet());
        return "admin/chitiet-dhn";
    }

    @GetMapping("/ds-chitiet-dhn/form")
    public String addOrUpdateChiTiet(Model model) {
        Chitietdonhangnhap chitiet = new Chitietdonhangnhap();
        model.addAttribute("chitiet", chitiet);
        return "admin/form_chitiet_dhn";
    }

    @PostMapping("/ds-chitiet-dhn/form")
    public String addOrUpdateChiTiet(@ModelAttribute("chitiet") Chitietdonhangnhap chitiet) {
        this.chitietService.addOrUpdateChiTiet(chitiet);
        this.dhnService.updateTongTienHang(chitiet.getIDDonHang().getId());
        return "redirect:/admin/ds-chitiet-dhn";
    }

    @GetMapping("/ds-chitiet-dhn/update/{id}")
    public String updateChiTiet(Model model, @PathVariable(value = "id") int id) {
        Chitietdonhangnhap chitiet = this.chitietService.getChiTietById(id);
        model.addAttribute("chitiet", chitiet);
        return "admin/form_chitiet_dhn";
    }

    @DeleteMapping("/ds-chitiet-dhn/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChiTiet(@PathVariable(value = "id") int id) {
        Chitietdonhangnhap chitiet = this.chitietService.getChiTietById(id);
        this.chitietService.deleteChiTiet(id);
        this.dhnService.updateTongTienHang(chitiet.getIDDonHang().getId());
    }
}
