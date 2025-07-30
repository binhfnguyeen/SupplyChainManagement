/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.Doitacvanchuyen;
import com.scm.repositories.DoiTacVanChuyenRepositoy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
public class DoiTacVanChuyenRepositoyImpl implements DoiTacVanChuyenRepositoy{
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addDoitacvanchuyen(Doitacvanchuyen dtvc) {
        Session s= this.factory.getObject().getCurrentSession();
        s.persist(dtvc);
    }

    @Override
    public List<Doitacvanchuyen> getDoiTacVanChuyen(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b=s.getCriteriaBuilder();
        CriteriaQuery<Doitacvanchuyen> q=b.createQuery(Doitacvanchuyen.class);
        Root root= q.from(Doitacvanchuyen.class);
        
        q.select(root);
        if(params !=null){
            List<Predicate> predicates= new ArrayList<>();
            String name=params.get("Name");
            if(name!=null && !name.isEmpty())
                predicates.add(b.like(root.get("ten"), name));
            
             q.where(predicates.toArray(Predicate[]::new));
        }
        Query query=s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Doitacvanchuyen getDoitacvanchuyenById(int id) {
        Session s =this.factory.getObject().getCurrentSession();
        
        Query q=s.createNamedQuery("Doitacvanchuyen.findById", Doitacvanchuyen.class);
        q.setParameter("id", id);
        return (Doitacvanchuyen) q.getSingleResult();
    }

    @Override
    public void addOrUpdateDtvc(Doitacvanchuyen dtvc) {
        Session s = this.factory.getObject().getCurrentSession();
        if (dtvc.getId() == null){
            s.persist(dtvc);
        } else {
            s.merge(dtvc);
        }
    }

    @Override
    public void deleteDtvc(int id) {
       Session s = this.factory.getObject().getCurrentSession();
       Doitacvanchuyen dtvc=this.getDoitacvanchuyenById(id);
       s.remove(dtvc);
    }
    
}
