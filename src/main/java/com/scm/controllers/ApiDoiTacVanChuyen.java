/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Doitacvanchuyen;
import com.scm.services.DoiTacVanChuyenService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiDoiTacVanChuyen {

    @Autowired
    private DoiTacVanChuyenService dtvcService;

    @PostMapping("/DoiTacVanChuyen")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDoiTacVanChuyen(@RequestBody Doitacvanchuyen dtvc) {
        this.dtvcService.addDoiTacVanChuyen(dtvc);
    }
    
    
    @GetMapping("/secure/DoiTacVanChuyen")
    public ResponseEntity<List<Doitacvanchuyen>> list(@RequestParam Map<String,String>params){
        return new ResponseEntity<>(this.dtvcService.getDoiTacVanChuyen(params),HttpStatus.OK);
    }

    @GetMapping("/secure/test")
    @PreAuthorize("hasRole('NHANVIEN')")
    public String test() {
        return "Đã chặn đúng";
    }

    @GetMapping("/DoiTacVanChuyen/{dtID}")
    public ResponseEntity<Doitacvanchuyen> retrieve(@PathVariable(value = "dtID") int id) {
        return new ResponseEntity<>(this.dtvcService.getDoitacvanchuyenById(id), HttpStatus.OK);
    }

}
