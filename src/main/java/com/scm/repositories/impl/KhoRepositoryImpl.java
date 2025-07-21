/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Kho;
import com.scm.repositories.KhoRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
public class KhoRepositoryImpl implements KhoRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Kho> getAllKho(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Kho> q = b.createQuery(Kho.class);

        Root khoRoot = q.from(Kho.class);
        q.select(khoRoot);
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Kho getKhoById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Kho.class, id);
    }

    @Override
    public void addOrUpdateKho(Kho kho) {
       Session s = this.factory.getObject().getCurrentSession();
       
       if (kho.getId() == null){
           s.persist(kho);
       } else {
           s.merge(kho);
       }
    }

    @Override
    public void deleteKho(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Kho kho = this.getKhoById(id);
        s.remove(kho);
    }

}
