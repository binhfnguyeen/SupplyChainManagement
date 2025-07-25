/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Hoadonxuat;
import com.scm.pojo.Vanchuyen;
import com.scm.repositories.HoaDonXuatRepository;
import com.scm.repositories.VanChuyenRepository;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class HoaDonXuatRepositoryImpl implements HoaDonXuatRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private VanChuyenRepository vcRepo;

    @Override
    public void addHoaDonXuat(Donhangxuat dhx) {
       Session s= this.factory.getObject().getCurrentSession();
       Hoadonxuat hdx = new Hoadonxuat();
       hdx.setIDDonHang(dhx);
       Vanchuyen vc=dhx.getIDVanChuyen();
       BigDecimal total=BigDecimal.ZERO;
       hdx.setTongChiPhi(total.add(dhx.getTongTien()).add(this.vcRepo.getSoTien(vc)));
       s.persist(hdx);
    }
    
}
