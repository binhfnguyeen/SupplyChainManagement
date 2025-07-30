/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Chitietdonhangxuat;
import com.scm.pojo.Donhangxuat;
import com.scm.repositories.ChiTietDonHangXuatRepository;
import com.scm.repositories.DonHangXuatReponsitory;
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
    
    
    
    @Override
    public void addChiTiet(Chitietdonhangxuat chitiet) {
        Session s = this.factory.getObject().getCurrentSession();

    if (chitiet.getId() == null) {
        // Lấy đối tượng đơn hàng xuất
        Donhangxuat dhx = chitiet.getIDDonHang();

        if (dhx == null || dhx.getId() == null) {
            throw new IllegalArgumentException("Đơn hàng xuất không hợp lệ.");
        }

        // Lấy bản đầy đủ của Donhangxuat từ DB để đảm bảo đầy đủ dữ liệu
        Donhangxuat fullDhx = s.get(Donhangxuat.class, dhx.getId());
        if (fullDhx == null) {
            throw new IllegalArgumentException("Không tìm thấy đơn hàng xuất trong DB.");
        }

        // Tính tổng tiền mới
        BigDecimal tongCu = fullDhx.getTongTien() != null ? fullDhx.getTongTien() : BigDecimal.ZERO;
        BigDecimal gia = this.sp_nccRepo.getGia(chitiet.getIDSanPham(), chitiet.getIDNhaCungCap());

        if (gia == null) {
            throw new IllegalArgumentException("Không tìm thấy giá của sản phẩm với nhà cung cấp.");
        }

        BigDecimal thanhTien = gia.multiply(BigDecimal.valueOf(chitiet.getSoLuong()));
        System.out.println(thanhTien);
        fullDhx.setTongTien(tongCu.add(thanhTien));

        // Gán lại đơn hàng đã đầy đủ và managed
        chitiet.setIDDonHang(fullDhx);

        // Lưu cả hai: đơn hàng đã cập nhật và chi tiết mới
        s.merge(fullDhx);      // cập nhật đơn hàng
        s.merge(chitiet);      // lưu chi tiết
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
    
}
