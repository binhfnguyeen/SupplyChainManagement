/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Kho;
import com.scm.pojo.Nhacungcap;
import com.scm.services.KhoService;
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
public class KhoController {
    @Autowired
    private KhoService khoService;
    
    @RequestMapping("/kho")
    public String dsKho(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("kho", this.khoService.getAllKho(params));
        return "admin/kho";
    }
    
    @DeleteMapping("/kho/{khoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKho(@PathVariable(value = "khoId") int id) {
        this.khoService.deleteKho(id);
    }
    
    @GetMapping("/kho/form")
    public String addOrUpdateKho(Model model) {
        Kho kho = new Kho();
        

        model.addAttribute("kho", kho);

        return "admin/form_kho";
    }
    @PostMapping("/kho/form")
    public String addOrUpdateKho(@ModelAttribute("kho") Kho kho) {
        this.khoService.addOrUpdateKho(kho);
        return "redirect:/admin/kho";
    }
    
    @GetMapping("/kho/update/{id}")
    public String updateKho(Model model, @PathVariable(value = "id") int id) {
        Kho kho = this.khoService.getKhoById(id);
        model.addAttribute("kho", kho);
        return "admin/form_kho";
    }
}
