/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Khachhang;
import com.scm.services.KhachHangService;
//import java.util.List;
//import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Admin
 */
public class ApiKhachHangController {
    @Autowired
    private KhachHangService khService;
    
    @PostMapping("/KhachHang")
    @ResponseStatus(HttpStatus.CREATED)
    public void addKhachHang(@RequestBody Khachhang kh) {
        this.khService.addOrUpdateKhachHangWithUser(kh);
    }
    
//    @GetMapping("/DonHangXuat")
//    public ResponseEntity<List<Khachhang>> list(@RequestParam Map<String, String> params){
//        String name =params.get("username");
//        return new ResponseEntity<>(this.khService.getKhachHangByKhachHangName(name),HttpStatus.OK);
//    }
}
