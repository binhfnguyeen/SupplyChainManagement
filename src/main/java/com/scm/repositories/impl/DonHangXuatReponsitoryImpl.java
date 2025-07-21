/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Cart;
import com.scm.pojo.Chitietdonhangxuat;
import com.scm.pojo.DonHangXuatRequest;
import com.scm.pojo.Donhangxuat;
import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.repositories.DonHangXuatReponsitory;
import com.scm.repositories.KhachHangRepository;
import com.scm.repositories.NhaCungCapReponsitory;
import com.scm.repositories.NhanVienRepository;
import com.scm.repositories.SanPhamNhaCungCapReponsitory;
import com.scm.repositories.SanPhamReponsitory;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
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
    private SanPhamReponsitory spRepo;
    
    @Autowired
    private NhanVienRepository nvRepo;
    
    @Autowired
    private NhaCungCapReponsitory nccRepo;
    
    @Autowired
    private SanPhamNhaCungCapReponsitory sp_nccRepo;
    
    
    @Override
    public void addDonHangXuat(DonHangXuatRequest dhxr) {
        if (dhxr != null) {
            Session s = this.factory.getObject().getCurrentSession();
            Donhangxuat dhx = new Donhangxuat();
            dhx.setIDKhachHang(this.khRepo.getKhachHangByKhachHangName("Công ty TNHH A"));
            dhx.setIDNhanVien(this.nvRepo.getNhanVienByID(dhxr.getIdNhanVien()));
            s.persist(dhx);
            
            BigDecimal total=BigDecimal.ZERO;

            for (var x : dhxr.getCarts()) {
                Chitietdonhangxuat c = new Chitietdonhangxuat();
                c.setSoLuong(x.getQuantity());
                c.setIDNhaCungCap(this.nccRepo.getNhaCungCapById(x.getIdNhaCungCap()));
                c.setIDSanPham(this.spRepo.getSanPhamById(x.getId()));
                c.setIDDonHang(dhx);
                total=total.add(this.sp_nccRepo.getGia(c.getIDSanPham(), c.getIDNhaCungCap()));
                s.persist(c);
            }
            
            dhx.setTongTien(total);
            s.persist(dhx);
        }
    }   
}
