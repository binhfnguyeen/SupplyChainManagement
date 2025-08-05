/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Hoadonxuat;
import com.scm.services.DonHangXuatService;
import com.scm.services.HoaDonXuatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/admin")
public class HoaDonXuatController {

    @Autowired
    private HoaDonXuatService hdxService;

    @Autowired
    private DonHangXuatService donHangXuatService;

    @ModelAttribute("dsDonHangXuat")
    public List<Donhangxuat> getDonHangXuat() {
        return this.donHangXuatService.getDonhangxuat(null);
    }

    @RequestMapping("/ds-hoadonxuat")
    public String list(Model model) {
        model.addAttribute("hoadonxuat", this.hdxService.getDsHoaDon(null));
        return "admin/hoadonxuat";
    }

    @GetMapping("/ds-hoadonxuat/form")
    public String addOrUpdateHoaDonXuat(Model model) {
        Hoadonxuat hoadon = new Hoadonxuat();
        model.addAttribute("hoadonxuat", hoadon);
        return "admin/form_hdx";
    }
   

    @PostMapping("/ds-hoadonxuat/form")
    public String addOrUpdateHoaDonXuat(@ModelAttribute("hoadonxuat") Hoadonxuat hdx) {
        Integer donHangId = hdx.getIDDonHang() != null ? hdx.getIDDonHang().getId() : null;

        if (donHangId != null) {
            Donhangxuat dhx = this.donHangXuatService.getDonhangxuatById(donHangId);
            if (dhx != null) {
                this.hdxService.addHoaDonXuat(dhx);
            }
        }
        return "redirect:/admin/ds-hoadonxuat";
    }
}
