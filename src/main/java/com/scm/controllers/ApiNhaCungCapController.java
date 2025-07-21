/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.services.NhaCungCapService;
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
public class ApiNhaCungCapController {
    @Autowired
    private NhaCungCapService nccService;
    
    @GetMapping("/ds-nhacungcap")
    public ResponseEntity<List<Nhacungcap>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.nccService.getDsNhaCungCap(params), HttpStatus.OK);
    }
    
    @GetMapping("/ds-nhacungcap/{nccID}")
    public ResponseEntity<Nhacungcap> retrieve(@PathVariable(value = "nccID") int id) {
        return new ResponseEntity<>(this.nccService.getNCCById(id), HttpStatus.OK);
    }
    
    @PostMapping("/ds-nhacungcap")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrUpdateSanpham(@RequestBody Nhacungcap ncc){
        this.nccService.addOrUpdateNCC(ncc);
    }
    
    @DeleteMapping("/ds-nhacungcap/{nccID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNhaCungCap(@PathVariable(value = "nccID") int id){
        this.nccService.deleteNCC(id);
    }
}
