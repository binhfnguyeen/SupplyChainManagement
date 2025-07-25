/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Chitietdonhangnhap;
import com.scm.repositories.ChiTietDonHangNhapRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dell
 */
@Repository
@Transactional
public class ChiTietDonHangNhapRepositoryImpl implements ChiTietDonHangNhapRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public void addChiTiet(Chitietdonhangnhap chitiet) {
        Session s = this.factory.getObject().getCurrentSession();
        
        if (chitiet.getId() == null){
            s.persist(chitiet);
        }
    }
    
}
