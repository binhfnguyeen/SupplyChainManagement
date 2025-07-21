/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Cart;
import com.scm.pojo.DonHangXuatRequest;
import com.scm.repositories.DonHangXuatReponsitory;
import com.scm.services.DonHangXuatService;
import java.util.List;
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

    
    
   
    
}
