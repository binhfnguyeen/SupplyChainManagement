/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Chitietdonhangxuat;
import com.scm.dto.DonHangXuatRequest;
import com.scm.pojo.Donhangxuat;
import com.scm.pojo.User;
import com.scm.repositories.DonHangXuatReponsitory;
import com.scm.repositories.KhachHangRepository;
import com.scm.repositories.NhaCungCapRepository;
import com.scm.repositories.NhanVienRepository;
import com.scm.repositories.SanPhamNhaCungCapRepository;
import com.scm.repositories.SanPhamRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
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
 * @author Admin
 */
@Repository
@Transactional
public class DonHangXuatReponsitoryImpl implements DonHangXuatReponsitory{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private KhachHangRepository khRepo;
    
    @Autowired
    private SanPhamRepository spRepo;
    
//    @Autowired
//    private NhanVienRepository nvRepo;
    
    @Autowired
    private NhaCungCapRepository nccRepo;
    
    @Autowired
    private SanPhamNhaCungCapRepository sp_nccRepo;
    
    @Override
    public void addDonHangXuat(Donhangxuat dhx) {
        Session s = this.factory.getObject().getCurrentSession();
        if (dhx.getId() == null) {
            s.persist(dhx);
        }
    }
    
    @Override
    public void addDonHangXuatWithUser(DonHangXuatRequest dhxr,User u) {
        if (dhxr != null) {
            Session s = this.factory.getObject().getCurrentSession();
            Donhangxuat dhx = new Donhangxuat();
            dhx.setIDKhachHang(this.khRepo.getKhachHangByKhachHangName("Công ty TNHH A"));
            dhx.setIDNhanVien(u.getNhanvien());
            s.persist(dhx);
            
            BigDecimal total=BigDecimal.ZERO;

            for (var x : dhxr.getCarts()) {
                Chitietdonhangxuat c = new Chitietdonhangxuat();
                c.setSoLuong(x.getQuantity());
                c.setIDNhaCungCap(this.nccRepo.getNCCById(x.getIdNhaCungCap()));
                c.setIDSanPham(this.spRepo.getSanPhamById(x.getId()));
                c.setIDDonHang(dhx);
                total=total.add(this.sp_nccRepo.getGia(c.getIDSanPham(), c.getIDNhaCungCap()).multiply(BigDecimal.valueOf(x.getQuantity())));
                s.persist(c);
            }
            
            dhx.setTongTien(total);
            s.persist(dhx);
        }
    }   

    @Override
    public List<Donhangxuat> getDonhangxuat(Map<String, String> params) {
         Session s = this.factory.getObject().getCurrentSession();
         CriteriaBuilder b=s.getCriteriaBuilder();
         CriteriaQuery<Donhangxuat> q=b.createQuery(Donhangxuat.class);
         Root root= q.from(Donhangxuat.class);
         q.select(root);        
         if (params!=null){
             List<Predicate> predicates= new ArrayList<>();
             String idKH = params.get("idKhachHang");
             if(idKH!=null && !idKH.isEmpty()){
                 predicates.add(b.equal(root.get("iDKhachHang").get("id"),Integer.valueOf(idKH) ));
             }
             
             String idNV = params.get("idNhanVien");
             if(idNV!=null && !idNV.isEmpty()){
                 predicates.add(b.equal(root.get("iDNhanVien").get("id"),Integer.valueOf(idNV) ));
             }
             String idVC = params.get("idVanChuyen");
             if(idVC!=null && !idVC.isEmpty()){
                 predicates.add(b.equal(root.get("iDVanChuyen").get("id"),Integer.valueOf(idVC) ));
             }
             q.where(predicates.toArray(Predicate[]::new));
         }
         
         
         Query query=s.createQuery(q);
         
         return query.getResultList();
         
    }

    @Override
    public Donhangxuat getDonhangxuatById(int id) {
        Session s =this.factory.getObject().getCurrentSession();
        Query q= s.createNamedQuery("Donhangxuat.findById", Donhangxuat.class);
        q.setParameter("id", id);
        
        return (Donhangxuat) q.getSingleResult();
    }

    @Override
    public void UpdateDonHangXuat(Donhangxuat dhx) {
        Session s = this.factory.getObject().getCurrentSession();
        if (dhx.getId() == null) {
            throw new IllegalArgumentException("ID của đơn hàng xuất không được null.");
        } else {
            s.merge(dhx);
        }
    }

    @Override
    public void deleteDonHangXuat(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Donhangxuat dhx = this.getDonhangxuatById(id);
        s.remove(dhx);
    }
}
