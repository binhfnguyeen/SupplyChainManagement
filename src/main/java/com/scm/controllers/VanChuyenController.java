/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Doitacvanchuyen;
import com.scm.pojo.Vanchuyen;
import com.scm.services.DoiTacVanChuyenService;
import com.scm.services.VanChuyenService;
import java.util.List;
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
 * @author Dell
 */
@Controller
@RequestMapping("/admin")
public class VanChuyenController {

    @Autowired
    private VanChuyenService vcService;

    @Autowired
    private DoiTacVanChuyenService dtvcService;

    @ModelAttribute("dsDoiTac")
    public List<Doitacvanchuyen> getDoiTac() {
        return this.dtvcService.getDoiTacVanChuyen(null);
    }

    @RequestMapping("/ds-vanchuyen")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("vanchuyen", this.vcService.getAllVanChuyen(params));
        return "admin/vanchuyen";
    }

    @GetMapping("/ds-vanchuyen/form")
    public String addOrUpdateNhanVien(Model model) {
        Vanchuyen vc = new Vanchuyen();

        model.addAttribute("vanchuyen", vc);

        return "admin/form_vc";
    }

    @PostMapping("/ds-vanchuyen/form")
    public String addOrUpdateVanChuyen(@ModelAttribute("vanchuyen") Vanchuyen vc) {
        this.vcService.addOrUpdateVanChuyen(vc);
        return "redirect:/admin/ds-vanchuyen";
    }

    @GetMapping("/ds-vanchuyen/update/{id}")
    public String updateVanChuyen(Model model, @PathVariable(value = "id") int id) {
        Vanchuyen vc = this.vcService.getVanChuyenById(id);
        model.addAttribute("vanchuyen", vc);
        return "admin/form_vc";
    }
    
    @DeleteMapping("/ds-vanchuyen/{vcId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVanChuyen(@PathVariable(value = "vcId") int id) {
        this.vcService.deleteVanChuyen(id);
    }
}
