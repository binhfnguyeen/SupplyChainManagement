/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.dto.ChiTietDonHangXuatResponse;
import com.scm.dto.DonHangXuatRequest;
import com.scm.pojo.Donhangxuat;
import com.scm.pojo.User;
import com.scm.services.ChiTietDonHangXuatService;
import com.scm.services.DonHangXuatService;
import com.scm.services.UserService;
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
    
    private UserService userService;
    
    @Autowired
    private ChiTietDonHangXuatService ctdhxRepo;
    
    
    @PostMapping("/secure/DonHangXuat")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> addToCart(@RequestBody DonHangXuatRequest dhxr,Principal principal) {
        int newOrderId = this.dhxService.addDonHangXuatWithUser(dhxr, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrderId);
    }
    
    
    @GetMapping("secure/DonHangXuat")
    public ResponseEntity<List<Donhangxuat>> list(@RequestParam Map<String, String> params){
//        User u=this.userService.getUserByUsername(principal.getName());
//        if(u.getRole().equals("KHACHHANG")){
//            params.put("user", u.getUsername());
//        }
        
        
        return new ResponseEntity<>(this.dhxService.getDonhangxuat(params),HttpStatus.OK);
    }
    
    
    
    @GetMapping("secure/SpDonHangXuat/{dhID}")
    public ResponseEntity<List<ChiTietDonHangXuatResponse>> getspofdh(@PathVariable(value = "dhID") int id) {
        return new ResponseEntity<>(this.ctdhxRepo.getDsSanPham(id), HttpStatus.OK);
    }
    
    @GetMapping("secure/DonHangXuat/{dhID}")
    public ResponseEntity<Donhangxuat> retrieve(@PathVariable(value = "dhID") int id) {
        return new ResponseEntity<>(this.dhxService.getDonhangxuatById(id), HttpStatus.OK);
    }
    
    @PostMapping("secure/updateDonHangXuat")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateDonHangXuat(@RequestBody Donhangxuat dhx){

        this.dhxService.UpdateDonhangxuat(dhx);
    }
    
    @DeleteMapping("secure/DonHangXuat/{dhID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDonHangXuat(@PathVariable(value = "dhID") int id){
        this.dhxService.deleteDonhangxuat(id);
    }
    
    @GetMapping("/secure/Khachhang/{khId}/don-hang")
    public ResponseEntity<List<Donhangxuat>> getByUserId(@PathVariable("khId") int userId){
        return new ResponseEntity<>(this.dhxService.getByUserId(userId), HttpStatus.OK);
    }
    
    @GetMapping("/secure/Nhanvien/{nvId}/don-hang")
    public ResponseEntity<List<Donhangxuat>> getByNhanVienId(@PathVariable("nvId") int nvId){
        return new ResponseEntity<>(this.dhxService.getByNhanVienId(nvId), HttpStatus.OK);
    }
    
    @PostMapping("secure/donhangxuat/part-update")
    public ResponseEntity<?> partUpdateDonHangXuat(@RequestBody Donhangxuat dhx){
        this.dhxService.partUpdateDonhangXuat(dhx);
        return ResponseEntity.ok("Đã cập nhật đơn hàng xuất thành công.");
    }
}
