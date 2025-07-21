/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.Kho;
import com.scm.services.KhoService;
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
public class ApiKhoController {
    @Autowired
    private KhoService khoService;
    
    @GetMapping("/ds-kho")
    public ResponseEntity<List<Kho>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.khoService.getAllKho(params), HttpStatus.OK);
    }
    
    @GetMapping("/ds-kho/{khoID}")
    public ResponseEntity<Kho> retrieve(@PathVariable(value = "khoID") int id) {
        return new ResponseEntity<>(this.khoService.getKhoById(id), HttpStatus.OK);
    }
    
    @PostMapping("/ds-kho")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrUpdateSanpham(@RequestBody Kho kho){
        this.khoService.addOrUpdateKho(kho);
    }
    
    @DeleteMapping("/ds-kho/{khoID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKho(@PathVariable(value = "khoID") int id){
        this.khoService.deleteKho(id);
    }
}
