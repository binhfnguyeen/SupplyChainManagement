/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.dto.HoaDonNhapResponse;
import com.scm.services.HoaDonNhapService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dell
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiHoaDonNhapController {

    @Autowired
    private HoaDonNhapService hoaDonNhapService;

    @PostMapping("/secure/donhangnhap/{dhID}/xuat-hoadon")
    public ResponseEntity<?> create(@PathVariable(value = "dhID") int id) {
        try {
            this.hoaDonNhapService.xuatHoaDonNhap(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Xuất hóa đơn nhập thành công");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Lỗi: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();;
            return ResponseEntity.internalServerError().body("Lỗi hệ thống");
        }
    }

    @GetMapping("/secure/ds-hoadonnhap")
    public ResponseEntity<List<HoaDonNhapResponse>> list(@RequestParam Map<String, String> params) {
        List<HoaDonNhapResponse> ds = hoaDonNhapService.getAllHoaDonNhap(params);
        return ResponseEntity.ok(ds);
    }

    @GetMapping("/secure/ds-hoadonnhap/{hdID}")
    public ResponseEntity<HoaDonNhapResponse> retrieve(@PathVariable(value = "hdID") int id) {
        HoaDonNhapResponse res = hoaDonNhapService.getHoaDonNhapById(id);
        return ResponseEntity.ok(res);
    }
}
