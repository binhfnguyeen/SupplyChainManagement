/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Danhgia;
import com.scm.pojo.Nhacungcap;
import com.scm.services.DanhGiaService;
import com.scm.services.NhaCungCapService;
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
public class DanhGiaController {

    @Autowired
    private DanhGiaService dgService;

    @Autowired
    private NhaCungCapService nccService;

    @ModelAttribute("dsNhaCungCap")
    public List<Nhacungcap> getNhaCungCap() {
        return this.nccService.getDsNhaCungCap(null);
    }

    @RequestMapping("/ds-danhgia")
    public String list(Model model) {
        model.addAttribute("danhgia", this.dgService.getAllDanhGia());
        return "admin/danhgia";
    }

    @GetMapping("/ds-danhgia/form")
    public String addOrUpdateDanhGia(Model model) {
        Danhgia dg = new Danhgia();

        model.addAttribute("danhgia", dg);

        return "admin/form_dg";
    }

    @PostMapping("/ds-danhgia/form")
    public String addOrUpdateDanhGia(@ModelAttribute("danhgia") Danhgia dg) {
        this.dgService.addOrUpdateDanhGia(dg);
        return "redirect:/admin/ds-danhgia";
    }

    @GetMapping("/ds-danhgia/update/{id}")
    public String updateDanhGia(Model model, @PathVariable(value = "id") int id) {
        Danhgia dg = this.dgService.getDanhGiaById(id);
        model.addAttribute("danhgia", dg);
        return "admin/form_dg";
    }

    @DeleteMapping("/ds-danhgia/{dgId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDanhGia(@PathVariable(value = "dgId") int id) {
        this.dgService.deleteDanhGia(id);
    }
}
