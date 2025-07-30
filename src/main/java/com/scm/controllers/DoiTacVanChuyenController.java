/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Doitacvanchuyen;
import com.scm.services.DoiTacVanChuyenService;
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
public class DoiTacVanChuyenController {
    
    @Autowired
    private DoiTacVanChuyenService dtvcService;
    
    @RequestMapping("/doitacvanchuyen")
    public String dsDoiTacVanChuyen(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("doitacvanchuyen", this.dtvcService.getDoiTacVanChuyen(null));
        return "admin/doitacvanchuyen";
    }
    
    @DeleteMapping("/doitacvanchuyen/{dtvcId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoiTacVanChuyen(@PathVariable(value = "dtvcId") int id) {
        this.dtvcService.deleteDtvc(id);
    }
    
     @GetMapping("/doitacvanchuyen/form")
    public String addOrUpdateDoiTacVanChuyen(Model model) {
        Doitacvanchuyen dtvc = new Doitacvanchuyen();
        model.addAttribute("doitacvanchuyen", dtvc);

        return "admin/form_dtvc";
    }
    
    @PostMapping("/doitacvanchuyen/form")
    public String addOrUpdateDoiTacVanChuyen(@ModelAttribute("doitacvanchuyen") Doitacvanchuyen dtvc) {
        this.dtvcService.addOrUpdateDtvc(dtvc);
        return "redirect:/admin/doitacvanchuyen";
    }
    
    @GetMapping("/doitacvanchuyen/update/{id}")
    public String updateDoiTacVanChuyen(Model model, @PathVariable(value = "id") int id) {
        Doitacvanchuyen dtvc = this.dtvcService.getDoitacvanchuyenById(id);
        model.addAttribute("doitacvanchuyen", dtvc);
        return "admin/form_dtvc";
    }
}
