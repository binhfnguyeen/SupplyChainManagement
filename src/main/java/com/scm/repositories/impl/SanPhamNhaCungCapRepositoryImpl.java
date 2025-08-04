/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.dto.ChiTietDonHangXuatResponse;
import com.scm.dto.SanphamNccDTO;
import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.pojo.SanphamNhacungcap;
import com.scm.repositories.SanPhamNhaCungCapRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
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
 * @author Dell
 */
@Repository
@Transactional
public class SanPhamNhaCungCapRepositoryImpl implements SanPhamNhaCungCapRepository {
    
    private static final int PAGE_SIZE = 6;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void add(SanphamNhacungcap spncc) {
        Session session = this.factory.getObject().getCurrentSession();

        if (spncc.getId() == null) {
            session.persist(spncc);
        }
    }

    @Override
    public List<SanphamNhacungcap> findByIDNhaCungCap(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<SanphamNhacungcap> q = b.createQuery(SanphamNhacungcap.class);
        Root<SanphamNhacungcap> root = q.from(SanphamNhacungcap.class);

        q.select(root).where(b.equal(root.get("iDNhaCungCap").get("id"), id));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public BigDecimal getGia(Sanpham s, Nhacungcap ncc) {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createNamedQuery("SanphamNhacungcap.findBySanphamAndNcc", SanphamNhacungcap.class);

        q.setParameter("sp", s);
        q.setParameter("ncc", ncc);

        try {
            SanphamNhacungcap spncc = (SanphamNhacungcap) q.getSingleResult();
            return spncc.getGia();
        } catch (NoResultException ex) {
            System.err.println("Không tìm thấy giá sản phẩm cho nhà cung cấp này!");
            return BigDecimal.ZERO;
        }
    }

//    @Override
//    public List<SanphamNhacungcap> getAllSanPhamNhaCungCap() {
//        Session s = this.factory.getObject().getCurrentSession();
//        String hql = "SELECT s.iDSanPham.tenSanPham, s.iDNhaCungCap.tenNhaCungCap, s.gia "
//               + "FROM SanphamNhacungcap s";
//    
//        Query query = s.createQuery(hql);
//
////        Query query = s.createQuery(q);
//        return query.getResultList();
//    }
    
   
    
    @Override
    public List<SanphamNccDTO> getAllSanPhamNhaCungCap(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        String hql = "SELECT new com.scm.dto.SanphamNccDTO(s.iDSanPham.ten, s.iDNhaCungCap.ten, s.gia,s.iDSanPham.id,s.iDNhaCungCap.id,s.id,s.iDSanPham.hinh) "
               + "FROM SanphamNhacungcap s";
    
        Query<SanphamNccDTO> query = s.createQuery(hql, SanphamNccDTO.class);
        
        if (params != null && params.containsKey("page")) {
            int page = Integer.parseInt(params.get("page"));
            int start = (page - 1) * PAGE_SIZE;
            query.setFirstResult(start);
            query.setMaxResults(PAGE_SIZE);
        }

//        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public SanphamNhacungcap getSPNCCById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(SanphamNhacungcap.class, id);
    }

    @Override
    public void addOrUpdate(SanphamNhacungcap spcc) {
        Session session = this.factory.getObject().getCurrentSession();

        if (spcc.getId() == null) {
            session.persist(spcc);
        } else {
            session.merge(spcc);
        }
    }

    @Override
    public void deleteSanPhamNhaCungCap(int id) {
        Session session = this.factory.getObject().getCurrentSession();

        SanphamNhacungcap spncc = this.getSPNCCById(id);
        session.remove(spncc);
    }

    @Override
    public SanphamNhacungcap findBySanpham(Sanpham sp) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<SanphamNhacungcap> query = session.createQuery(
                "FROM SanphamNhacungcap s WHERE s.iDSanPham.id = :spId",
                SanphamNhacungcap.class
        );
        query.setParameter("spId", sp.getId());
        return query.getResultStream().findFirst().orElse(null);
    }
}
