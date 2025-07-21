/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Nhanvien;
import com.scm.pojo.User;
import com.scm.repositories.NhanVienRepository;
import jakarta.persistence.Query;
<<<<<<< Updated upstream
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
=======
>>>>>>> Stashed changes
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
public class NhanVienRepositoryImpl implements NhanVienRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    private static final int PAGE_SIZE = 8;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Nhanvien add(Nhanvien nv) {
        Session s = this.factory.getObject().getCurrentSession();
        s.persist(nv);
        return nv;
    }

    @Override
<<<<<<< Updated upstream
    public List<Nhanvien> getDsNhanVien(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Nhanvien> q = b.createQuery(Nhanvien.class);
        Root root = q.from(Nhanvien.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add((Predicate) b.like(root.get("hoTen"), String.format("%%%s%%", kw)));
            }

            String cv = params.get("cv");
            if (cv != null && !cv.isEmpty()) {
                predicates.add(b.equal(root.get("chucVu"), cv));
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
    public void deleteNhanVien(Integer id) {
        Session s = this.factory.getObject().getCurrentSession();
        Nhanvien nv = this.getNhanvienById(id);
        if (nv != null) {
            User u = nv.getUserID();
            s.remove(nv);
            if (u != null) {
                s.remove(u);
            }
        }
    }

    @Override
    public Nhanvien getNhanvienById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Nhanvien.class, id);
    }

    @Override
    public void addOrUpdateNhanvienWithUser(Nhanvien nv) {
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

    @Override
    public int soNhanVien() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> q = b.createQuery(Long.class);

        Root<Nhanvien> nvRoot = q.from(Nhanvien.class);
        q.select(b.count(nvRoot.get("id")));

        Query query = s.createQuery(q);
        Long result = (Long) query.getSingleResult();
        return result.intValue();
    }

=======
    public Nhanvien getNhanVienByID(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Nhanvien.findById", Nhanvien.class);
        q.setParameter("id", id);
        
        return (Nhanvien) q.getSingleResult();
    }
    
>>>>>>> Stashed changes
}
