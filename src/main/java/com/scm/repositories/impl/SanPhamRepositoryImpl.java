/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Kho;
import com.scm.pojo.Sanpham;
import com.scm.repositories.SanPhamRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dell
 */
@Repository
@Transactional
public class SanPhamRepositoryImpl implements SanPhamRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Sanpham getSanPhamById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Sanpham.class, id);
    }

    @Override
    public void addOrUpdateSanpham(Sanpham sp) {
        Session s = this.factory.getObject().getCurrentSession();

        if (sp.getId() == null) {
            s.persist(sp);
        } else {
            s.merge(sp);
        }
    }

    @Override
    public List<Sanpham> getAllSanpham(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Sanpham> q = b.createQuery(Sanpham.class);

        Root spRoot = q.from(Sanpham.class);
        q.select(spRoot);
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void deleteSanpham(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Sanpham sp = this.getSanPhamById(id);
        s.remove(sp);
    }

}
