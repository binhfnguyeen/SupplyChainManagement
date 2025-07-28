/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Donhangnhap;
import com.scm.pojo.Hoadonnhap;
import com.scm.services.DonHangNhapService;
import com.scm.services.HoaDonNhapService;
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
public class HoaDonNhapController {

    @Autowired
    private HoaDonNhapService hdnService;

    @Autowired
    private DonHangNhapService donHangNhapService;

    @ModelAttribute("dsDonHangNhap")
    public List<Donhangnhap> getDonHangNhap() {
        return this.donHangNhapService.getAllDonHangNhap();
    }

    @RequestMapping("/ds-hoadonnhap")
    public String list(Model model) {
        model.addAttribute("hoadonnhap", this.hdnService.getDsHoaDon(null));
        return "admin/hoadonnhap";
    }

    @GetMapping("/ds-hoadonnhap/form")
    public String addOrUpdateHoaDonNhap(Model model) {
        Hoadonnhap hoadon = new Hoadonnhap();
        model.addAttribute("hoadonnhap", hoadon);
        return "admin/form_hdn";
    }

    @PostMapping("/ds-hoadonnhap/form")
    public String addOrUpdateHoaDonNhap(@ModelAttribute("hoadonnhap") Hoadonnhap hdn) {
        Integer donHangId = hdn.getIDDonHang() != null ? hdn.getIDDonHang().getId() : null;

        if (donHangId != null) {
            Donhangnhap dhn = this.donHangNhapService.getDonHangNhapById(donHangId);
            if (dhn != null) {
                hdn.setIDDonHang(dhn);
                hdn.setTongChiPhi(dhn.getTongTien());
            }
        }

        this.hdnService.addOrUpdateHoaDonNhap(hdn);
        return "redirect:/admin/ds-hoadonnhap";
    }

    @GetMapping("/ds-hoadonnhap/update/{id}")
    public String updateHoaDonNhap(Model model, @PathVariable(value = "id") int id) {
        Hoadonnhap hoadon = this.hdnService.getHoaDonById(id);
        model.addAttribute("hoadonnhap", hoadon);
        return "admin/form_hdn";
    }

    @DeleteMapping("/ds-hoadonnha/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHoaDon(@PathVariable(value = "id") int id) {
        this.hdnService.deleteHoaDonNhap(id);
    }
}
