/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.dto.ChiTietDonHangXuatResponse;
import com.scm.pojo.Chitietdonhangxuat;
import com.scm.pojo.Donhangxuat;
import com.scm.repositories.ChiTietDonHangXuatRepository;
import com.scm.repositories.DonHangXuatReponsitory;
import com.scm.repositories.NhaCungCapRepository;
import com.scm.repositories.SanPhamNhaCungCapRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
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
public class ChiTietDonHangXuatRepositoryImpl implements ChiTietDonHangXuatRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private SanPhamNhaCungCapRepository sp_nccRepo;
    
    @Autowired
    private DonHangXuatReponsitory dhxRepo;
    
    @Autowired
    private NhaCungCapRepository nccRepo;
    
    
    
    @Override
    public void addChiTiet(Chitietdonhangxuat chitiet) {
        Session s = this.factory.getObject().getCurrentSession();

    if (chitiet.getId() == null) {
        
        Donhangxuat dhx = chitiet.getIDDonHang();
        
        System.out.println(chitiet.getIDNhaCungCap());
        

        if (dhx == null || dhx.getId() == null) {
            throw new IllegalArgumentException("Đơn hàng xuất không hợp lệ.");
        }

        Donhangxuat fullDhx = s.get(Donhangxuat.class, dhx.getId());
        if (fullDhx == null) {
            throw new IllegalArgumentException("Không tìm thấy đơn hàng xuất trong DB.");
        }

        BigDecimal tongCu = fullDhx.getTongTien() != null ? fullDhx.getTongTien() : BigDecimal.ZERO;
        BigDecimal gia = this.sp_nccRepo.getGia(chitiet.getIDSanPham(), chitiet.getIDNhaCungCap());
        
        System.out.println(chitiet.getIDNhaCungCap());
        System.out.println(gia);
        
        if (gia == null) {
            throw new IllegalArgumentException("Không tìm thấy giá của sản phẩm với nhà cung cấp.");
        }

        BigDecimal thanhTien = gia.multiply(BigDecimal.valueOf(chitiet.getSoLuong()));
        System.out.println(thanhTien);
        fullDhx.setTongTien(tongCu.add(thanhTien));

        chitiet.setIDDonHang(fullDhx);

        s.merge(fullDhx);      
        s.merge(chitiet);      
    }
    }

    @Override
    public void deleteChiTietByDonHangId(int id) {
       Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Chitietdonhangxuat> q = b.createQuery(Chitietdonhangxuat.class);
        Root<Chitietdonhangxuat> root = q.from(Chitietdonhangxuat.class);
        q.select(root).where(b.equal(root.get("iDDonHang").get("id"), id));
        
        var rsList = s.createQuery(q).getResultList();
        for (Chitietdonhangxuat ct: rsList){
            s.remove(ct);
        }
    }

    @Override
    public List<Chitietdonhangxuat> getAllChiTiet() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Chitietdonhangxuat> q = b.createQuery(Chitietdonhangxuat.class);
        Root<Chitietdonhangxuat> root = q.from(Chitietdonhangxuat.class);
        q.select(root);
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Chitietdonhangxuat getChiTietById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        
        return s.get(Chitietdonhangxuat.class, id);
    }

    @Override
    public void updateChiTiet(Chitietdonhangxuat chitiet) {
        Session s = this.factory.getObject().getCurrentSession();

        if (chitiet.getId() != null) {
            s.merge(chitiet);
        }
    }

    @Override
    public void deleteChiTiet(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        
        Chitietdonhangxuat chitiet = this.getChiTietById(id);
        s.remove(chitiet);
    }
    
     @Override
     public List<ChiTietDonHangXuatResponse> getDsSanPham(int id){
        Session s =this.factory.getObject().getCurrentSession();
        String hql="SELECT new com.scm.dto.ChiTietDonHangXuatResponse(s.iDSanPham.id,s.iDSanPham.ten,s.soLuong) "
                +"FROM Chitietdonhangxuat s "
                +"WHERE s.iDDonHang.id = :id";
        Query<ChiTietDonHangXuatResponse> query= s.createQuery(hql,ChiTietDonHangXuatResponse.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    
}
