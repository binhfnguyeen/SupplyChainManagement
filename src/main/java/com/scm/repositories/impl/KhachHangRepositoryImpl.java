/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Khachhang;
import com.scm.repositories.KhachHangRepository;
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
public class KhachHangRepositoryImpl implements KhachHangRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public Khachhang add(Khachhang kh) {
       Session s = this.factory.getObject().getCurrentSession();
        s.persist(kh);
        return kh;
    }
    
}
