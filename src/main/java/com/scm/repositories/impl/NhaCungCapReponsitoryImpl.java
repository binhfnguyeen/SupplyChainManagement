/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Nhanvien;
import com.scm.repositories.NhaCungCapReponsitory;
import jakarta.persistence.Query;
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
public class NhaCungCapReponsitoryImpl implements NhaCungCapReponsitory{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public Nhacungcap getNhaCungCapById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Nhacungcap.findById", Nhacungcap.class);
        q.setParameter("id", id);
        
        return (Nhacungcap) q.getSingleResult();
    }
    
}
