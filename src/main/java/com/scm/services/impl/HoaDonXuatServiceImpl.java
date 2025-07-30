/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Hoadonxuat;
import com.scm.repositories.HoaDonXuatRepository;
import com.scm.services.HoaDonXuatService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class HoaDonXuatServiceImpl implements HoaDonXuatService{

    @Autowired
    private HoaDonXuatRepository hdxRepo;
    
    @Override
    public void addHoaDonXuat(Donhangxuat dhx) {
        this.hdxRepo.addHoaDonXuat(dhx);
    }

    @Override
    public Hoadonxuat getHoaDonXuatById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void xuatHoaDonXuat(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Hoadonxuat> getAllHoaDonXuat(Map<String, String> params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addOrUpdateHoaDonXuat(Hoadonxuat hdx) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Hoadonxuat> getDsHoaDon(Map<String, String> params) {
        return this.hdxRepo.getAllHoaDonXuat(null);
    }
    
}
