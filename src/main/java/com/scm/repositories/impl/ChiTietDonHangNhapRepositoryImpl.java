/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Chitietdonhangnhap;
import com.scm.repositories.ChiTietDonHangNhapRepository;
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
public class ChiTietDonHangNhapRepositoryImpl implements ChiTietDonHangNhapRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addChiTiet(Chitietdonhangnhap chitiet) {
        Session s = this.factory.getObject().getCurrentSession();

        if (chitiet.getId() == null) {
            s.persist(chitiet);
        }
    }

    @Override
    public void deleteChiTietByDonHangId(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Chitietdonhangnhap> q = b.createQuery(Chitietdonhangnhap.class);
        Root<Chitietdonhangnhap> root = q.from(Chitietdonhangnhap.class);
        q.select(root).where(b.equal(root.get("iDDonHang").get("id"), id));
        
        var rsList = s.createQuery(q).getResultList();
        for (Chitietdonhangnhap ct: rsList){
            s.remove(ct);
        }
    }

    @Override
    public List<Chitietdonhangnhap> getAllChiTiet() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Chitietdonhangnhap> q = b.createQuery(Chitietdonhangnhap.class);
        Root<Chitietdonhangnhap> root = q.from(Chitietdonhangnhap.class);
        q.select(root);
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Chitietdonhangnhap getChiTietById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        
        return s.get(Chitietdonhangnhap.class, id);
    }

    @Override
    public void updateChiTiet(Chitietdonhangnhap chitiet) {
        Session s = this.factory.getObject().getCurrentSession();

        if (chitiet.getId() != null) {
            s.merge(chitiet);
        }
    }

    @Override
    public void deleteChiTiet(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        
        Chitietdonhangnhap chitiet = this.getChiTietById(id);
        s.remove(chitiet);
    }

}
