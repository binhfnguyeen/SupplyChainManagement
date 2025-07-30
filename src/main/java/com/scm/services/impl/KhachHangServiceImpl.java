/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Khachhang;
import com.scm.repositories.KhachHangRepository;
import com.scm.services.KhachHangService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class KhachHangServiceImpl implements KhachHangService{
    
    @Autowired
    private KhachHangRepository khRepository;
    
    @Override
    public int soKhachHang() {
        return this.khRepository.soKhachHang();
    }


    @Override
    public List<Khachhang> getDsKhachHang(Map<String, String> params) {
        return this.khRepository.getDsKhachHang(params);
    }

    @Override
    public void deleteKhachHang(Integer id) {
        this.khRepository.deleteKhachHang(id);
    }

    @Override
    public void addOrUpdateKhachHangWithUser(Khachhang kh) {
        this.khRepository.addOrUpdateKhachHangWithUser(kh);
    }

    @Override
    public Khachhang getKhachHangById(int id) {
        return this.khRepository.getKhachHangById(id);
    }
    
}
