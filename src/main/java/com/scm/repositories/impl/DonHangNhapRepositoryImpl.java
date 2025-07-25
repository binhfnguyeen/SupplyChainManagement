/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Donhangnhap;
import com.scm.repositories.DonHangNhapRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
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
public class DonHangNhapRepositoryImpl implements DonHangNhapRepository {

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
        root.fetch("chitietdonhangnhapSet", JoinType.LEFT);
        root.fetch("iDKho", JoinType.LEFT);
        root.fetch("iDNhanVien", JoinType.LEFT);
        root.fetch("iDVanChuyen", JoinType.LEFT);

        q.select(root).distinct(true);

        return s.createQuery(q).getResultList();
    }

    @Override
    public Donhangnhap getDonHangNhapById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Donhangnhap.class, id);
    }

    @Override
    public void updateDonHangNhap(Donhangnhap dhn) {
        Session s = this.factory.getObject().getCurrentSession();
        if (dhn.getId() != null) {
            s.merge(dhn);
        }
    }

    @Override
    public void deleteDonHangNhap(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Donhangnhap dhn = this.getDonHangNhapById(id);

        s.remove(dhn);
    }

}
