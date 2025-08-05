/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;


import com.scm.dto.HoaDonXuatResponse;
import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Hoadonxuat;
import com.scm.services.DonHangXuatService;
import com.scm.services.HoaDonXuatService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class ApiHoaDonXuatController {
    
    
    @Autowired
    private HoaDonXuatService hdxService;
    
    @Autowired
    private DonHangXuatService dhxService;
    
//    @PostMapping("/secure/donHoaDonXuat")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addHoaDonXuat(@RequestBody Donhangxuat dhx) {
//        this.hdxService.addHoaDonXuat(dhx);
//    }
    
    @PostMapping("/secure/donhangxuat/{dhID}/xuat-hoadon")
    public ResponseEntity<?> create(@PathVariable(value = "dhID") int id) {
        try {
            Donhangxuat dhx=this.dhxService.getDonhangxuatById(id);
            this.hdxService.addHoaDonXuat(dhx);
            return ResponseEntity.status(HttpStatus.CREATED).body("Xuất hóa đơn xuất thành công");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Lỗi: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();;
            return ResponseEntity.internalServerError().body("Lỗi hệ thống");
        }
    }
    
    @PostMapping("/secure/hoadonxuat/{dhID}")
    public ResponseEntity<?> addHoaDonXuatById(@PathVariable(value = "dhID") int id) {
//        try {
            this.hdxService.addHoaDonXuatById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Xuất hóa đơn xuất thành công");
//        } catch (IllegalArgumentException ex) {
//            return ResponseEntity.badRequest().body("Lỗi: " + ex.getMessage());
//        } catch (Exception ex) {
//            ex.printStackTrace();;
//            return ResponseEntity.internalServerError().body("Lỗi hệ thống");
//        }
    }
    
    @GetMapping("secure/HoaDonXuatOfUser")
    public ResponseEntity<List<Hoadonxuat>> list(@RequestParam Map<String, String> params,Principal principal){
//        User u=this.userService.getUserByUsername(principal.getName());
//        if(u.getRole().equals("KHACHHANG")){
//            params.put("user", u.getUsername());
//        }
        String username=principal.getName();
        return new ResponseEntity<>(this.hdxService.getAllHoaDonXuatByUser(params,username),HttpStatus.OK);
    }
    
    @GetMapping("/secure/ds-hoadonxuat")
    public ResponseEntity<List<HoaDonXuatResponse>> list(@RequestParam Map<String, String> params) {
        List<HoaDonXuatResponse> ds = hdxService.getAllHoaDonXuat(params);
        return ResponseEntity.ok(ds);
    }
    
    @GetMapping("/secure/ds-hoadonxuat/{hdID}")
    public ResponseEntity<HoaDonXuatResponse> retrieve(@PathVariable(value = "hdID") int id) {
        HoaDonXuatResponse res = hdxService.getHoaDonNhapById(id);
        return ResponseEntity.ok(res);
    }
}
