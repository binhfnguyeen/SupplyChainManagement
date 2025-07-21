/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Danhgia;
import com.scm.services.DanhGiaService;
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
public class ApiDanhGiaController {
    @Autowired
    private DanhGiaService dgService;
    
    @PostMapping("/danhgia")
    public ResponseEntity<String> danhgia(@RequestBody Danhgia dg){
        try {
            this.dgService.addDanhGia(dg);
            return ResponseEntity.ok().body("Thêm đánh giá thành công");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Thêm đánh giá thất bại: " + ex.getMessage());
        }
    }
    
    @GetMapping("/nhacungcap/{nccId}/danhgia")
    public ResponseEntity<List<Danhgia>> getDanhGiaByNCC(@PathVariable(value = "nccId") int nccId) {
        List<Danhgia> danhGiaList = this.dgService.findDanhGiaByNCC(nccId);
        return ResponseEntity.ok(danhGiaList);
    }
}
