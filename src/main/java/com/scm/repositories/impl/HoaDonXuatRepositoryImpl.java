/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Hoadonxuat;
import com.scm.pojo.Vanchuyen;
import com.scm.repositories.HoaDonXuatRepository;
import com.scm.repositories.VanChuyenRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
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
 * @author Admin
 */
@Repository
@Transactional
public class HoaDonXuatRepositoryImpl implements HoaDonXuatRepository{
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private VanChuyenRepository vcRepo;

    @Override
    public void addHoaDonXuat(Donhangxuat dhx) {
       Session s= this.factory.getObject().getCurrentSession();
       Hoadonxuat hdx = new Hoadonxuat();
       hdx.setIDDonHang(dhx);
       Vanchuyen vc=dhx.getIDVanChuyen();
       BigDecimal total=BigDecimal.ZERO;
       hdx.setTongChiPhi(total.add(dhx.getTongTien()).add(this.vcRepo.getSoTien(vc)));
       s.persist(hdx);
    }

    @Override
    public void addOrUpdateHoaDonNhap(Hoadonxuat hdx) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    @Override
    public List<Hoadonxuat> getAllHoaDonXuat(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Hoadonxuat> q = b.createQuery(Hoadonxuat.class);

        Root root = q.from(Hoadonxuat.class);
        var donHangJoin = root.fetch("iDDonHang", JoinType.LEFT);

        donHangJoin.fetch("chitietdonhangxuatSet", JoinType.LEFT);

        q.select(root).distinct(true);

        Query<Hoadonxuat> query = s.createQuery(q);
        
        System.out.println(query.getResultList());
        return query.getResultList();
    }
    
}
