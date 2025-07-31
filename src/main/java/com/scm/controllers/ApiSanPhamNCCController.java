/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.dto.SanphamNhacungcapRequest;
import com.scm.pojo.SanphamNhacungcap;
import com.scm.services.SanPhamNhaCungCapService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class ApiSanPhamNCCController {
    @Autowired
    private SanPhamNhaCungCapService spnccService;
    
    @PostMapping("/secure/nhacungcap/sanpham")
    public ResponseEntity<?> addSanPhamToNhaCungCap(@RequestBody SanphamNhacungcapRequest data) {
        try {
            spnccService.addSanPhamToNhaCungCap(data.getIdNhaCungCap(), data.getIdSanPham(), data.getGia());
            return ResponseEntity.ok("Thêm sản phẩm thuộc nhà cung cấp thành công");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Lỗi: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Lỗi hệ thống");
        }
    }
    
    @GetMapping("/nhacungcap/{nccId}/sanpham")
    public ResponseEntity<?> getSanPhamCuaNhaCungCap(@PathVariable("nccId") int nccId) {
        try {
            List<SanphamNhacungcap> result = spnccService.getSanPhamCuaNhaCungCap(nccId);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Không thể lấy danh sách sản phẩm của nhà cung cấp");
        }
    }
}
