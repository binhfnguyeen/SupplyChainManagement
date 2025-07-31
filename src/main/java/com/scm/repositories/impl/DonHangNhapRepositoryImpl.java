/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Chitietdonhangnhap;
import com.scm.pojo.Donhangnhap;
import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.repositories.DonHangNhapRepository;
import com.scm.repositories.SanPhamNhaCungCapRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
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
public class DonHangNhapRepositoryImpl implements DonHangNhapRepository {

    private static final int PAGE_SIZE = 6;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private SanPhamNhaCungCapRepository spnccRepo;

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
        
        if(params != null){
            List<Predicate> predicates = new ArrayList<>();
            String kho = params.get("kho");
            if (kho != null && !kho.isEmpty()) {
                predicates.add(b.like(root.get("iDKho").get("diaChi"), String.format("%%%s%%", kho)));
            }
        }

        Query<Donhangnhap> query = s.createQuery(q);

        if (params != null && params.containsKey("page")) {
            int page = Integer.parseInt(params.get("page"));
            int start = (page - 1) * PAGE_SIZE;
            query.setFirstResult(start);
            query.setMaxResults(PAGE_SIZE);
        }

        return query.getResultList();
    }

    @Override
    public Donhangnhap getDonHangNhapById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Donhangnhap> cq = cb.createQuery(Donhangnhap.class);
        Root<Donhangnhap> root = cq.from(Donhangnhap.class);
        root.fetch("chitietdonhangnhapSet", JoinType.LEFT);
        cq.select(root).where(cb.equal(root.get("id"), id)).distinct(true);

        return s.createQuery(cq).getSingleResult();
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
