/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Vanchuyen;
import com.scm.services.VanChuyenService;
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
public class ApiVanChuyenController {

    @Autowired
    private VanChuyenService vcService;

    @PostMapping("/ds-vanchuyen")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrUpdateVanChuyen(@RequestBody Vanchuyen vc) {
        this.vcService.addOrUpdateVanChuyen(vc);
    }

    @GetMapping("/ds-vanchuyen")
    public ResponseEntity<List<Vanchuyen>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.vcService.getAllVanChuyen(params), HttpStatus.OK);
    }

    @GetMapping("/ds-vanchuyen/{vcID}")
    public ResponseEntity<Vanchuyen> retrieve(@PathVariable(value = "vcID") int id) {
        return new ResponseEntity<>(this.vcService.getVanChuyenById(id), HttpStatus.OK);
    }

    @DeleteMapping("/ds-vanchuyen/{vcID}")
    public ResponseEntity<?> delete(@PathVariable(value = "vcID") int id) {
        this.vcService.deleteVanChuyen(id);
        return ResponseEntity.ok("Đã xóa vận chuyển có ID: " + id);
    }
}
