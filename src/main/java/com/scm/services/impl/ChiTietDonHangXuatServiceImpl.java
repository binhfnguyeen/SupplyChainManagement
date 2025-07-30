/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Chitietdonhangxuat;
import com.scm.repositories.ChiTietDonHangXuatRepository;
import com.scm.services.ChiTietDonHangXuatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ChiTietDonHangXuatServiceImpl implements ChiTietDonHangXuatService{
    
    @Autowired
    private ChiTietDonHangXuatRepository chitietRepository;
    
    @Override
    public List<Chitietdonhangxuat> getAllChiTiet() {
        return this.chitietRepository.getAllChiTiet();
    }

    @Override
    public Chitietdonhangxuat getChiTietById(int id) {
       return this.chitietRepository.getChiTietById(id);
    }

    @Override
    public void addOrUpdateChiTiet(Chitietdonhangxuat chitiet) {
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
