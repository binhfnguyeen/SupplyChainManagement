/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.dto.KhoSanphamRequest;
import com.scm.pojo.KhoSanpham;
import com.scm.services.QuanLyKhoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class ApiQuanLyKhoController {

    @Autowired
    private QuanLyKhoService qlKhoService;
    
    @PostMapping("/kho/them-sanpham")
    public ResponseEntity<?> addSanPhamToKho(@RequestBody KhoSanphamRequest data) {
        try {
            qlKhoService.addSanPhamToKho(data.getIdKho(), data.getIdSanPham(), data.getSoLuong(), data.getHanSuDung());
            return ResponseEntity.ok("Thêm sản phẩm vào kho thành công");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Lỗi: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Lỗi hệ thống");
        }
    }
    
    @GetMapping("kho/{khoId}/sanpham")
    public ResponseEntity<?> getSanPhamTrongKho(@PathVariable("khoId") int khoId) {
        try {
            List<KhoSanpham> result = qlKhoService.getSanPhamTrongKho(khoId);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Không thể lấy danh sách sản phẩm trong kho");
        }
    }
}
