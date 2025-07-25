/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Hoadonnhap;
import com.scm.repositories.HoaDonNhapRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class HoaDonNhapRepositoryImpl implements HoaDonNhapRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public void addOrUpdateHoaDonNhap(Hoadonnhap hdn) {
        Session s = this.factory.getObject().getCurrentSession();
        
        if (hdn.getId() == null){
            s.persist(hdn);
        } else {
            s.merge(hdn);
        }
    }

    @Override
    public List<Hoadonnhap> getAllHoaDonNhap(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Hoadonnhap> q = b.createQuery(Hoadonnhap.class);

        Root root = q.from(Hoadonnhap.class);
        q.select(root);
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Hoadonnhap getHoaDonNhapById(int id) {
       Session s = this.factory.getObject().getCurrentSession();
       
       return s.get(Hoadonnhap.class, id);
    }

    @Override
    public void deleteHoaDonNhap(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        
        Hoadonnhap hdn = this.getHoaDonNhapById(id);
        
        if(hdn != null){
            s.remove(hdn);
        }
    }
    
}
