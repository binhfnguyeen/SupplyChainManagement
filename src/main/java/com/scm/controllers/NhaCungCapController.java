/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Nhacungcap;
import com.scm.services.NhaCungCapService;
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
 * @author Admin
 */
@Controller
@RequestMapping("/admin")
public class NhaCungCapController {
    @Autowired
    private NhaCungCapService nccService;
    
    @RequestMapping("/supplier")
    public String dsNhaCungCap(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("nhacungcap", this.nccService.getDsNhaCungCap(params));
        System.err.println(this.nccService.getDsNhaCungCap(params));
        return "admin/nhacungcap";
    }
    
    @DeleteMapping("/supplier/{empId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNhaCungCap(@PathVariable(value = "nccId") int id) {
        this.nccService.deleteNCC(id);
    }
    
     @GetMapping("/supplier/form")
    public String addOrUpdateNhaCungCap(Model model) {
        Nhacungcap ncc = new Nhacungcap();
        

        model.addAttribute("nhacungcap", ncc);

        return "admin/form_ncc";
    }
    
    @PostMapping("/supplier/form")
    public String addOrUpdateNhanvienWithUser(@ModelAttribute("nhacungcap") Nhacungcap ncc) {
        this.nccService.addOrUpdateNCC(ncc);
        return "redirect:/admin/supplier";
    }
    
    @GetMapping("/supplier/update/{id}")
    public String updateNhaCungCap(Model model, @PathVariable(value = "id") int id) {
        Nhacungcap ncc = this.nccService.getNCCById(id);
        model.addAttribute("nhacungcap", ncc);
        return "admin/form_ncc";
    }
}
