/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Nhanvien;
import com.scm.pojo.Sanpham;
import com.scm.pojo.SanphamNhacungcap;
import com.scm.repositories.SanPhamNhaCungCapReponsitory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import java.math.BigDecimal;
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
public class SanPhamNhaCungCapReponsitoryImpl implements SanPhamNhaCungCapReponsitory{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public BigDecimal getGia(Sanpham s, Nhacungcap ncc) {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createNamedQuery("SanphamNhacungcap.findBySanphamAndNcc", SanphamNhacungcap.class);

        q.setParameter("sp", s);
        q.setParameter("ncc", ncc);

        try {
            SanphamNhacungcap spncc = (SanphamNhacungcap) q.getSingleResult();
            return spncc.getGia();  // giả sử trường này là kiểu int
        } catch (NoResultException ex) {
            System.err.println("Không tìm thấy giá sản phẩm cho nhà cung cấp này!");
            return BigDecimal.ZERO; // hoặc -1 tùy bạn
        }
        }
    
}
