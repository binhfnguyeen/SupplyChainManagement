/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Donhangnhap;
import com.scm.pojo.Sanpham;
import com.scm.repositories.DonHangNhapRepository;
import jakarta.data.repository.Repository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 *
 * @author Dell
 */
@Repository
public class DonHangNhapRepositoryImpl implements DonHangNhapRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public void addDonHangNhap(Donhangnhap dhn) {
        Session s = this.factory.getObject().getCurrentSession();
        if (dhn.getId() == null) {
            s.persist(dhn);
        }
    }

    @Override
    public List<Donhangnhap> getDonHangNhap(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Donhangnhap> q = b.createQuery(Donhangnhap.class);
        Root root = q.from(Donhangnhap.class);
        q.select(root);
        
        Query query = s.createQuery(q);
        return query.getResultList();
     }

    @Override
    public Donhangnhap getDonHangNhapById(int id) {
       Session s = this.factory.getObject().getCurrentSession();
       return s.get(Donhangnhap.class, id);
    }

    @Override
    public void updateDonHangNhap(Donhangnhap dhn) {
         Session s = this.factory.getObject().getCurrentSession();
        if (dhn.getId() == null) {
            s.persist(dhn);
        }
    }

    @Override
    public void deleteDonHangNhap(int id) {
         Session s = this.factory.getObject().getCurrentSession();
        Donhangnhap dhn = this.getDonHangNhapById(id);
        
        s.remove(dhn);
    }
    
}
