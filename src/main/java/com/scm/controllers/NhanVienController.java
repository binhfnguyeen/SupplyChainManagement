/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Nhanvien;
import com.scm.pojo.User;
import com.scm.services.NhanVienService;
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
public class NhanVienController {

    @Autowired
    private NhanVienService nvService;

    @RequestMapping("/employees")
    public String dsNhanVien(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("nhanvien", this.nvService.getDsNhanVien(params));
        return "admin/nhanvien";
    }

    @DeleteMapping("/employees/{empId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable(value = "empId") int id) {
        this.nvService.deleteNhanVien(id);
    }

    @GetMapping("/employees/form")
    public String addOrUpdateNhanVien(Model model) {
        Nhanvien nv = new Nhanvien();
        nv.setUserID(new User());

        model.addAttribute("nhanvien", nv);

        return "admin/form_nv";
    }

    @PostMapping("/employees/form")
    public String addOrUpdateNhanvienWithUser(@ModelAttribute("nhanvien") Nhanvien nv) {
        this.nvService.addOrUpdateNhanvienWithUser(nv);
        return "redirect:/admin/employees";
    }

    @GetMapping("/employees/update/{id}")
    public String updateNhanVien(Model model, @PathVariable(value = "id") int id) {
        Nhanvien nv = this.nvService.getNhanvienById(id);
        model.addAttribute("nhanvien", nv);
        return "admin/form_nv";
    }
}
