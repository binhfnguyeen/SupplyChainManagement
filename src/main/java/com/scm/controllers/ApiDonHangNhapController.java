/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.dto.DonHangNhapRequest;
import com.scm.dto.DonHangNhapResponse;
import com.scm.services.DonHangNhapService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiDonHangNhapController {
    @Autowired
    private DonHangNhapService dhnService;
    
    @PostMapping("/donhangnhap")
    public ResponseEntity<?> create(@RequestBody DonHangNhapRequest request){
        try {
            this.dhnService.createDonHangNhap(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Thêm đơn hàng nhập thành công");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Lỗi: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Lỗi hệ thống");
        }
    }
    
    @GetMapping("/ds-donhangnhap")
    public ResponseEntity<List<DonHangNhapResponse>> list(Map<String, String> params){
        return new ResponseEntity<>(this.dhnService.getAllDonHangNhap(params),HttpStatus.OK);
    }
    
    @PutMapping("/donhangnhap/{dhID}")
    public ResponseEntity<?> update(@RequestBody DonHangNhapRequest request, @PathVariable(value = "dhID") int id){
        try {
            this.dhnService.updateDonHangNhap(request, id);
            return ResponseEntity.status(HttpStatus.OK).body("Cập nhật đơn hàng nhập thành công");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Lỗi: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Lỗi hệ thống");
        }
    }
}
