/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.dto.DonHangXuatRequest;
import com.scm.pojo.Donhangxuat;
import com.scm.services.DonHangXuatService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiDonHangXuatController {
    @Autowired
    private DonHangXuatService dhxService;
    
    
    @PostMapping("/secure/DonHangXuat")
    @PreAuthorize("hasAnyRole('ADMIN','NHANVIEN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addToCart(@RequestBody DonHangXuatRequest dhxr,Principal principal) {
        this.dhxService.addExportInvoice(dhxr,principal.getName());
    }
    
    
    @GetMapping("/DonHangXuat")
    public ResponseEntity<List<Donhangxuat>> list(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.dhxService.getDonhangxuat(params),HttpStatus.OK);
    }
    
    @GetMapping("/DonHangXuat/{dhID}")
    public ResponseEntity<Donhangxuat> retrieve(@PathVariable(value = "dhID") int id) {
        return new ResponseEntity<>(this.dhxService.getDonhangxuatById(id), HttpStatus.OK);
    }
    
    @PostMapping("/updateDonHangXuat")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateDonHangXuat(@RequestBody Donhangxuat dhx){

        this.dhxService.UpdateDonhangxuat(dhx);
    }
    
    @DeleteMapping("/DonHangXuat/{dhID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDonHangXuat(@PathVariable(value = "dhID") int id){
        this.dhxService.deleteDonhangxuat(id);
    }
}
