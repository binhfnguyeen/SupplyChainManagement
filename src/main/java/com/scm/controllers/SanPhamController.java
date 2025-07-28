/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Sanpham;
import com.scm.services.SanPhamService;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Dell
 */
@Controller
@RequestMapping("/admin")
public class SanPhamController {

    @Autowired
    private SanPhamService spService;

    @RequestMapping("/ds-sanpham")
    public String list(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("sanpham", this.spService.getAllSanpham(params));
        return "admin/sanpham";
    }

    @GetMapping("/ds-sanpham/form")
    public String addOrUpdateSanPham(Model model) {
        Sanpham sp = new Sanpham();
        model.addAttribute("sanpham", sp);
        return "admin/form_sp";
    }

    @PostMapping("/ds-sanpham/form")
    public String addOrUpdateSanPham(
            @ModelAttribute("sanpham") Sanpham sp,
            @RequestParam("file") MultipartFile file
    ) {
        this.spService.addOrUpdateSanpham(sp, file);
        return "redirect:/admin/ds-sanpham";
    }

    @GetMapping("/ds-sanpham/update/{id}")
    public String updateSanPham(Model model, @PathVariable(value = "id") int id) {
        Sanpham sp = this.spService.getSanPhamById(id);
        model.addAttribute("sanpham", sp);
        return "admin/form_sp";
    }

    @DeleteMapping("/ds-sanpham/{spId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSanPham(@PathVariable(value = "spId") int id) {
        this.spService.deleteSanpham(id);
    }
}
