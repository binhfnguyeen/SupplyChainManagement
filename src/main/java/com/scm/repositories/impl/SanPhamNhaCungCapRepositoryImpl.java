/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.pojo.SanphamNhacungcap;
import com.scm.repositories.SanPhamNhaCungCapRepository;
import jakarta.persistence.NoResultException;
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
 * @author Dell
 */
@Repository
@Transactional
public class SanPhamNhaCungCapRepositoryImpl implements SanPhamNhaCungCapRepository {

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

    @Override
    public List<SanphamNhacungcap> getAllSanPhamNhaCungCap() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<SanphamNhacungcap> q = b.createQuery(SanphamNhacungcap.class);

        Root root = q.from(SanphamNhacungcap.class);
        q.select(root);

        Query query = s.createQuery(q);
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
        
        if (spcc.getId() == null){
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
}
