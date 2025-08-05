/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Doitacvanchuyen;
import com.scm.repositories.DoiTacVanChuyenRepositoy;
import com.scm.services.DoiTacVanChuyenService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class DoiTacVanChuyenServiceImpl implements DoiTacVanChuyenService{
    
    @Autowired
    private DoiTacVanChuyenRepositoy dtvcRepo;

    @Override
    public List<Doitacvanchuyen> getDoiTacVanChuyen(Map<String, String> params) {
        return this.dtvcRepo.getDoiTacVanChuyen(params);
    }

    @Override
    public Doitacvanchuyen getDoitacvanchuyenById(int id) {
        return this.dtvcRepo.getDoitacvanchuyenById(id);
    }

    @Override
    public void addOrUpdateDtvc(Doitacvanchuyen dtvc) {
        this.dtvcRepo.addOrUpdateDtvc(dtvc);
    }

    @Override
    public void deleteDtvc(int id) {
        this.dtvcRepo.deleteDtvc(id);
    }
    
}
