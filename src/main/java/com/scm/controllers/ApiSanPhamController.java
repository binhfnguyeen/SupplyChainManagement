/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Sanpham;
import com.scm.services.SanPhamService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
 * @author Dell
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiSanPhamController {

    @Autowired
    private SanPhamService spService;

    @GetMapping("/ds-sanpham")
    public ResponseEntity<List<Sanpham>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.spService.getAllSanpham(params), HttpStatus.OK);
    }
    
    @GetMapping("/ds-sanpham/{spID}")
    public ResponseEntity<Sanpham> retrieve(@PathVariable(value = "spID") int id) {
        return new ResponseEntity<>(this.spService.getSanPhamById(id), HttpStatus.OK);
    }
    
    @PostMapping("/ds-sanpham")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrUpdateSanpham(@RequestBody Sanpham sp){
        this.spService.addOrUpdateSanpham(sp);
    }
    
    @DeleteMapping("/ds-sanpham/{spID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSanPham(@PathVariable(value = "spID") int id){
        this.spService.deleteSanpham(id);
    }
}
