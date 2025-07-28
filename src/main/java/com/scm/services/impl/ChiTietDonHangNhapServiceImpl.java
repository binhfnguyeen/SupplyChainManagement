/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Chitietdonhangnhap;
import com.scm.repositories.ChiTietDonHangNhapRepository;
import com.scm.services.ChiTietDonHangNhapService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class ChiTietDonHangNhapServiceImpl implements ChiTietDonHangNhapService{
    
    @Autowired
    private ChiTietDonHangNhapRepository chitietRepository;

    @Override
    public List<Chitietdonhangnhap> getAllChiTiet() {
        return this.chitietRepository.getAllChiTiet();
    }

    @Override
    public Chitietdonhangnhap getChiTietById(int id) {
        return this.chitietRepository.getChiTietById(id);
    }

    @Override
    public void addOrUpdateChiTiet(Chitietdonhangnhap chitiet) {
        if (chitiet.getId() == null){
            this.chitietRepository.addChiTiet(chitiet);
        } else {
            this.chitietRepository.updateChiTiet(chitiet);
        }
    }

    @Override
    public void deleteChiTiet(int id) {
        this.chitietRepository.deleteChiTiet(id);
    }
    
}
