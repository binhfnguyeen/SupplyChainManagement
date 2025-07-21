/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Nhacungcap;
import com.scm.repositories.NhaCungCapRepository;
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
public class NhaCungCapRepositoryImpl implements NhaCungCapRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Nhacungcap> getDsNhaCungCap(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Nhacungcap> q = b.createQuery(Nhacungcap.class);

        Root nccRoot = q.from(Nhacungcap.class);
        q.select(nccRoot);
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Nhacungcap getNCCById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Nhacungcap.class, id);
    }

    @Override
    public void addOrUpdateNCC(Nhacungcap ncc) {
        Session s = this.factory.getObject().getCurrentSession();
        if (ncc.getId() == null){
            s.persist(ncc);
        } else {
            s.merge(ncc);
        }
    }

    @Override
    public void deleteNCC(int id) {
       Session s = this.factory.getObject().getCurrentSession();
       Nhacungcap ncc = this.getNCCById(id);
       s.remove(ncc);
    }
    
}
