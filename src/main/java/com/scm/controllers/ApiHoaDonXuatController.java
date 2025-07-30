/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;


import com.scm.pojo.Donhangxuat;
import com.scm.services.HoaDonXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiHoaDonXuatController {
    
    
    @Autowired
    private HoaDonXuatService hdxService;
    
    @PostMapping("/secure/HoaDonXuat")
    @PreAuthorize("hasAnyRole('ADMIN','NHANVIEN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addHoaDonXuat(@RequestBody Donhangxuat dhx) {
        this.hdxService.addHoaDonXuat(dhx);
    }
}
