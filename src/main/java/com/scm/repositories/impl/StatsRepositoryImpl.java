/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Danhgia;
import com.scm.pojo.Donhangnhap;
import com.scm.pojo.Kho;
import com.scm.pojo.KhoSanpham;
import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.pojo.SanphamNhacungcap;
import com.scm.repositories.StatsRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
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
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> statsTonKho() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<KhoSanpham> root = q.from(KhoSanpham.class);
        Join<KhoSanpham, Sanpham> sanphamJoin = root.join("iDSanPham");
        Join<KhoSanpham, Kho> khoJoin = root.join("iDKho");

        Predicate expired = b.greaterThan(root.get("hanSuDung"), b.currentDate());

        q.multiselect(
                sanphamJoin.get("id"),
                sanphamJoin.get("ten"),
                khoJoin.get("diaChi"),
                root.get("soLuong"),
                root.get("hanSuDung")
        ).where(expired);

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object[]> statsHieuSuatNhaCungCap() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root<Nhacungcap> nccRoot = q.from(Nhacungcap.class);
        Join<Nhacungcap, Danhgia> dgJoin = nccRoot.join("danhgiaSet", JoinType.LEFT);
        Join<Nhacungcap, SanphamNhacungcap> spnccJoin = nccRoot.join("sanphamNhacungcapSet", JoinType.LEFT);
        Expression<Double> avgChatLuong = b.avg(dgJoin.get("chatLuong"));
        Expression<Double> tyleDungHan = b.avg(
                b.<Double>selectCase()
                .when(b.isTrue(dgJoin.get("giaoHangDungHan")), 1.0)
                .otherwise(0.0)
        );
        Expression<Double> giaTrungBinh = b.avg(spnccJoin.get("gia"));
        
        q.multiselect(
                nccRoot.get("id"),
                nccRoot.get("ten"),
                avgChatLuong,
                tyleDungHan,
                giaTrungBinh
        ).groupBy(nccRoot.get("id"), nccRoot.get("ten"));
        Query query = s.createQuery(q);
        return query.getResultList();
    }

}
