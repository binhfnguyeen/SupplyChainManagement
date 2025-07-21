/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.repositories.KhachHangRepository;
import com.scm.services.KhachHangService;
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
    
}
