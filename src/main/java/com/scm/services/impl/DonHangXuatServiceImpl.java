/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Cart;
import com.scm.pojo.DonHangXuatRequest;
import com.scm.pojo.Donhangxuat;
import com.scm.repositories.DonHangXuatReponsitory;
import com.scm.services.DonHangXuatService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class DonHangXuatServiceImpl implements DonHangXuatService{
    @Autowired
    private DonHangXuatReponsitory dhxRepo;

    @Override
    public void addExportInvoice(DonHangXuatRequest dhxr) {
       this.dhxRepo.addDonHangXuat(dhxr);
    }

    @Override
    public List<Donhangxuat> getDonhangxuat(Map<String, String> params) {
        return this.dhxRepo.getDonhangxuat(params);
    }

    @Override
    public Donhangxuat getDonhangxuatById(int id) {
        return this.dhxRepo.getDonhangxuatById(id);
    }

    @Override
    public void  UpdateDonhangxuat(Donhangxuat dhx) {
        this.dhxRepo.UpdateDonHangXuat(dhx);
    }

    @Override
    public void deleteDonhangxuat(int id) {
        this.dhxRepo.deleteDonHangXuat(id);
    }

    
    
   
    
}
