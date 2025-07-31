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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(path = "/secure/ds-sanpham", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addOrUpdateSanpham(@RequestParam(value = "id", required = false) Integer id,
            @RequestParam("ten") String ten,
            @RequestParam("hinh") MultipartFile hinh) {

        try {
            Sanpham sp = new Sanpham();
            if (id != null)
                sp.setId(id);
            sp.setTen(ten);
            this.spService.addOrUpdateSanpham(sp, hinh);
            return id != null ? ResponseEntity.status(HttpStatus.OK).body("Cập nhật sản phẩm thành công"): 
                    ResponseEntity.status(HttpStatus.OK).body("Thêm sản phẩm thành công");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Lỗi: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Lỗi hệ thống");
        }
    }

    @DeleteMapping("/secure/ds-sanpham/{spID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSanPham(@PathVariable(value = "spID") int id
    ) {
        this.spService.deleteSanpham(id);
    }
}
