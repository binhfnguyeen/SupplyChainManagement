/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Khachhang;
import com.scm.pojo.User;
import com.scm.repositories.KhachHangRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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

    @Override
    public int soKhachHang() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root<Khachhang> khRoot = q.from(Khachhang.class);
        q.select(b.count(khRoot.get("id")));

        Query query = s.createQuery(q);
        Long result = (Long) query.getSingleResult();
        return result.intValue();
    }

    @Override
    public Khachhang getKhachHangByKhachHangName(String name) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Khachhang.findByTen", Khachhang.class);
        q.setParameter("ten", name);
        
        return (Khachhang) q.getSingleResult();
    }
    
}
