/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Danhgia;
import com.scm.repositories.DanhGiaRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
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
public class DanhGiaRepositoryImpl implements DanhGiaRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addOrUpdateDanhGia(Danhgia dg) {
        Session session = this.factory.getObject().getCurrentSession();
        if (dg.getId() == null) {
            session.persist(dg);
        } else {
            session.merge(dg);
        }
    }

    @Override
    public List<Danhgia> findDanhGiaByNCC(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Danhgia> q = b.createQuery(Danhgia.class);
        Root<Danhgia> root = q.from(Danhgia.class);
        
        q.select(root).where(b.equal(root.get("iDNhaCungCap").get("id"), id));
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Danhgia> getAllDanhGia() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Danhgia> q = b.createQuery(Danhgia.class);

        Root root = q.from(Danhgia.class);
        q.select(root);
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void deleteDanhGia(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Danhgia dg = this.getDanhGiaById(id);
        s.remove(dg);
    }

    @Override
    public Danhgia getDanhGiaById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Danhgia.class, id);
    }

}
