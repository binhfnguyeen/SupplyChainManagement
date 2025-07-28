/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Khachhang;
import com.scm.pojo.User;
import com.scm.repositories.KhachHangRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dell
 */
@Repository 
@Transactional
public class KhachHangRepositoryImpl implements KhachHangRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    private static final int PAGE_SIZE = 8;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public Khachhang add(Khachhang kh) {
       Session s = this.factory.getObject().getCurrentSession();
        s.persist(kh);
        return kh;
    }

    @Override
    public int soKhachHang() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root<Khachhang> khRoot = q.from(Khachhang.class);
        q.select(b.count(khRoot.get("id")));

        Query query = s.createQuery(q);
        Long result = (Long) query.getSingleResult();
        return result.intValue();
    }

    @Override
    public Khachhang getKhachHangByKhachHangName(String name) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Khachhang.findByTen", Khachhang.class);
        q.setParameter("ten", name);
        
        return (Khachhang) q.getSingleResult();
    }

    @Override
    public List<Khachhang> getDsKhachHang(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Khachhang> q = b.createQuery(Khachhang.class);
        Root root = q.from(Khachhang.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add((Predicate) b.like(root.get("hoTen"), String.format("%%%s%%", kw)));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null) {
                int start = (Integer.parseInt(page) - 1) * PAGE_SIZE;

                query.setFirstResult(start);
                query.setMaxResults(PAGE_SIZE);
            }
        }

        return query.getResultList();
    }

    @Override
    public void deleteKhachHang(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        Khachhang kh = this.getKhachHangById(id);
        if (kh != null) {
            User u = kh.getUserID();
            s.remove(kh);
            if (u != null) {
                s.remove(u);
            }
        }
    }

    @Override
    public Khachhang getKhachHangById(int id) {
       Session s = this.factory.getObject().getCurrentSession();
        return s.get(Khachhang.class, id);
    }

    @Override
    public void addOrUpdateKhachHangWithUser(Khachhang nv) {
        Session s = this.factory.getObject().getCurrentSession();
        User u = nv.getUserID();

        if (u != null) {
            if (u.getId() == null) {
                u.setPassword(passwordEncoder.encode(u.getPassword()));
                s.persist(u);
            } else {
                User existingUser = s.get(User.class, u.getId());
                if (!u.getPassword().equals(existingUser.getPassword())) {
                    u.setPassword(passwordEncoder.encode(u.getPassword()));
                }
                s.merge(u);
            }
        }

        nv.setUserID(u);

        if (nv.getId() == null) {
            s.persist(nv);
        } else {
            s.merge(nv);
        }
    }
}
