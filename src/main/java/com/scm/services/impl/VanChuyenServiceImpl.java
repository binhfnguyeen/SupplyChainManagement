/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Vanchuyen;
import com.scm.repositories.VanChuyenRepository;
import com.scm.services.VanChuyenService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class VanChuyenServiceImpl implements VanChuyenService{
    
    @Autowired
    private VanChuyenRepository vcRepository;
    
    @Override
    public void addOrUpdateVanChuyen(Vanchuyen vc) {
       if (vc.getTinhTrang() == null || vc.getTinhTrang().trim().isEmpty()){
           throw new IllegalArgumentException("Tình trạng không được để trống.");
       }
       
       if (vc.getSoTien() == null || vc.getSoTien().doubleValue() < 0) {
            throw new IllegalArgumentException("Số tiền không hợp lệ (phải >= 0).");
        }
       
       this.vcRepository.addOrUpdateVanCuyen(vc);
    }

    @Override
    public List<Vanchuyen> getAllVanChuyen(Map<String, String> params) {
        return this.vcRepository.getAllVanChuyen(params);
    }

    @Override
    public void deleteVanChuyen(int id) {
        this.vcRepository.deleteVanChuyen(id);
    }

    @Override
    public Vanchuyen getVanChuyenById(int id) {
        return this.vcRepository.getVanChuyenById(id);
    }
    
}
