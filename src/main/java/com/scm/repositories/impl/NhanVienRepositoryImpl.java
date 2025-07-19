/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Nhanvien;
import com.scm.repositories.NhanVienRepository;
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
public class NhanVienRepositoryImpl implements NhanVienRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public Nhanvien add(Nhanvien nv) {
        Session s = this.factory.getObject().getCurrentSession();
        s.persist(nv);
        return nv;
    }
    
}
