/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Hoadonxuat;
import com.scm.pojo.User;
import com.scm.pojo.Vanchuyen;
import com.scm.repositories.DonHangXuatReponsitory;
import com.scm.repositories.HoaDonXuatRepository;
import com.scm.repositories.UserRepository;
import com.scm.repositories.VanChuyenRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private DonHangXuatReponsitory dhxRepo;

    @Override
    public void addHoaDonXuat(Donhangxuat dhx) {
       Session s= this.factory.getObject().getCurrentSession();
       Hoadonxuat hdx = new Hoadonxuat();
       hdx.setIDDonHang(dhx);
       Vanchuyen vc=dhx.getIDVanChuyen();
       BigDecimal total=BigDecimal.ZERO;
       Calendar calendar = Calendar.getInstance(); // ngày hôm nay
       dhx.setThoiGianNhan(calendar.getTime());
       hdx.setTongChiPhi(total.add(dhx.getTongTien()).add(this.vcRepo.getSoTien(vc)));
       s.persist(hdx);
       this.dhxRepo.UpdateDonHangXuat(dhx);
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
    
    @Override
    public List<Hoadonxuat> getAllHoaDonXuatByUser(Map<String, String> params,String username){
       Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Hoadonxuat> q = b.createQuery(Hoadonxuat.class);
        Root<Hoadonxuat> root = q.from(Hoadonxuat.class);
        q.select(root);

        Join<Object, Object> donHangJoin = root.join("iDDonHang");
        Join<Object, Object> khachHangJoin = donHangJoin.join("iDKhachHang");
        Join<Object, Object> userJoin = khachHangJoin.join("userID");  // assuming field name is "user"

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(userJoin.get("username"), username));

        if (params != null && params.containsKey("tinhTrang")) {
            predicates.add(b.equal(donHangJoin.get("tinhTrang"), params.get("tinhTrang")));
        }

        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);
        return query.getResultList();
        
    }
    
    @Override
    public Hoadonxuat getHoaDonXuatById(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        return s.get(Hoadonxuat.class, id);
    }

    @Override
    public void addHoaDonXuatById(int id) {
       Session s= this.factory.getObject().getCurrentSession();
       Hoadonxuat hdx = new Hoadonxuat();
       Donhangxuat dhx=this.dhxRepo.getDonhangxuatById(id);
       hdx.setIDDonHang(dhx);
       Vanchuyen vc=dhx.getIDVanChuyen();
       BigDecimal total=BigDecimal.ZERO;
       hdx.setTongChiPhi(total.add(dhx.getTongTien()).add(this.vcRepo.getSoTien(vc)));
       s.persist(hdx);
    }
}
