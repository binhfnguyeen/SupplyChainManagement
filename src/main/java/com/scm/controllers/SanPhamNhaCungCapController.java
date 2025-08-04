/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.pojo.SanphamNhacungcap;
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
public class SanPhamNhaCungCapController {

    @Autowired
    private SanPhamNhaCungCapService spnccService;

    @Autowired
    private SanPhamService spService;

    @Autowired
    private NhaCungCapService nccService;

    @ModelAttribute("dsSanPham")
    public List<Sanpham> getSanPham() {
        return this.spService.getAllSanpham(null);
    }

    @ModelAttribute("dsNCC")
    public List<Nhacungcap> getNhaCungCap() {
        return this.nccService.getDsNhaCungCap(null);
    }
    
    @RequestMapping("/ds-spncc")
    public String list(Model model) {
        model.addAttribute("spncc", this.spnccService.getAllSanPhamNhaCungCap(null));
        return "admin/spncc";
    }

    @GetMapping("/ds-spncc/form")
    public String addOrUpdateSPNCC(Model model) {
        SanphamNhacungcap spncc = new SanphamNhacungcap();
        model.addAttribute("spncc", spncc);
        return "admin/form_spncc";
    }

    @PostMapping("/ds-spncc/form")
    public String addOrUpdateSPNCC(@ModelAttribute("spncc") SanphamNhacungcap spncc) {
        this.spnccService.addOrUpdate(spncc);
        return "redirect:/admin/ds-spncc";
    }

    @GetMapping("/ds-spncc/update/{id}")
    public String updateSPNCC(Model model, @PathVariable(value = "id") int id) {
        SanphamNhacungcap spncc = this.spnccService.getSPNCCById(id);
        model.addAttribute("spncc", spncc);
        return "admin/form_spncc";
    }

    @DeleteMapping("/ds-spncc/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSPNCC(@PathVariable(value = "id") int id) {
        this.spnccService.deleteSanPhamNhaCungCap(id);
    }
}
