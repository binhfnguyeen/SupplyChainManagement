/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.KhoSanpham;
import com.scm.pojo.Sanpham;
import com.scm.repositories.KhoSanPhamRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
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
public class KhoSanPhamRepositoryImpl implements KhoSanPhamRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addKhoSanpham(KhoSanpham ksp) {
        Session s = this.factory.getObject().getCurrentSession();
        if (ksp.getId() == null) {
            s.persist(ksp);
        } 
    }

    @Override
    public List<KhoSanpham> getKhoSanphamByKhoId(int khoId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<KhoSanpham> q = b.createQuery(KhoSanpham.class);
        Root<KhoSanpham> root = q.from(KhoSanpham.class);
//        Join<KhoSanpham,Sanpham> join=root.join("iDSanPham",JoinType.RIGHT);
//        q.multiselect(join.get("id"),join.get("ten"),join.get("soLuong"));
        String hql = "SELECT ks FROM KhoSanpham ks "
               + "JOIN FETCH ks.iDSanPham sp "
               + "WHERE ks.iDKho.id = :khoId";
        Predicate predicate = b.equal(root.get("iDKho").get("id"), khoId);
        q.where(predicate);
        
        System.out.println(s.createQuery(hql, KhoSanpham.class)
            .setParameter("khoId", khoId)
            .getResultList());
        return s.createQuery(hql, KhoSanpham.class)
            .setParameter("khoId", khoId)
            .getResultList();
    }

    @Override
    public KhoSanpham findByKhoAndSanpham(int khoId, int sanPhamId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<KhoSanpham> q = b.createQuery(KhoSanpham.class);
        Root<KhoSanpham> root = q.from(KhoSanpham.class);

        q.select(root).where(
                b.equal(root.get("iDKho").get("id"), khoId),
                b.equal(root.get("iDSanPham").get("id"), sanPhamId)
        );

        Query<KhoSanpham> query = s.createQuery(q);
        return query.uniqueResult();
    }

    @Override
    public void updateKhoSanpham(KhoSanpham ksp) {
        Session s = this.factory.getObject().getCurrentSession();
        if (ksp.getId() != null) {
            s.merge(ksp);
        }
    }

}
