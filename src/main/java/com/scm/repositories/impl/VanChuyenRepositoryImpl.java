/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Sanpham;
import com.scm.pojo.Vanchuyen;
import com.scm.repositories.VanChuyenRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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
public class VanChuyenRepositoryImpl implements VanChuyenRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public void addOrUpdateVanCuyen(Vanchuyen vc) {
       Session s = this.factory.getObject().getCurrentSession();

        if (vc.getId() == null) {
            s.persist(vc);
        } else {
            s.merge(vc);
        }
    }

    @Override
    public List<Vanchuyen> getAllVanChuyen(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Vanchuyen> q = b.createQuery(Vanchuyen.class);

        Root vcRoot = q.from(Vanchuyen.class);
        q.select(vcRoot);
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void deleteVanChuyen(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Vanchuyen vc = this.getVanChuyenById(id);
        s.remove(vc);
    }

    @Override
    public Vanchuyen getVanChuyenById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Vanchuyen.class, id);
    }

    @Override
    public BigDecimal getSoTien(Vanchuyen vc) {
        Session s = this.factory.getObject().getCurrentSession();
        Vanchuyen v = s.get(Vanchuyen.class, vc.getId());  // Lấy lại từ DB theo ID
        return v.getSoTien();
    }
    
}
