/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Sanpham;
import com.scm.pojo.SanphamNhacungcap;
import com.scm.repositories.SanPhamReponsitory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class SanPhamReponsitoryImpl implements SanPhamReponsitory{

    @Autowired
    private LocalSessionFactoryBean factory;
    private static final int PAGE_SIZE = 6;
    
    @Override
    public List<Sanpham> getSanPham(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Sanpham> q = b.createQuery(Sanpham.class);
        Root root = q.from(Sanpham.class);
        q.select(root);

        if (params != null) {
            // Loc du lieu
            List<Predicate> predcates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predcates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }

            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                predcates.add(b.greaterThanOrEqualTo(root.get("price"), fromPrice));
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                predcates.add(b.lessThanOrEqualTo(root.get("price"), toPrice));
            }

            String cateId = params.get("cateId");
            if (cateId != null && !cateId.isEmpty()) {
                predcates.add(b.equal(root.get("category").as(Integer.class), cateId));
            }

            q.where(predcates.toArray(Predicate[]::new));

            // Sap xep du lieu
            q.orderBy(b.desc(root.get(params.getOrDefault("sortBy", "id"))));
        }

        Query query = s.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null) {
                int start = (Integer.parseInt(page) - 1) * PAGE_SIZE;

                query.setFirstResult(start);
                query.setMaxResults(PAGE_SIZE);
            }
        }
        return query.getResultList();
    }

    @Override
    public Sanpham getSanPhamById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Sanpham.class, id);
    }

    @Override
    public void addOrUpdateSanPham(Sanpham sp) {
          Session s = this.factory.getObject().getCurrentSession();
        if (sp.getId() == null) {
            s.persist(sp);
        } else {
            s.merge(sp);
        }
    }

    @Override
    public void deleteSanPham(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Sanpham p = this.getSanPhamById(id);
        s.remove(p);
    }
}
