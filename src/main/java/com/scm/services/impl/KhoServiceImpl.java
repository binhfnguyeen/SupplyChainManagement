/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Kho;
import com.scm.repositories.KhoRepository;
import com.scm.services.KhoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class KhoServiceImpl implements KhoService{
    
    @Autowired
    private KhoRepository khoRepo;
    
    @Override
    public List<Kho> getAllKho(Map<String, String> params) {
        return this.khoRepo.getAllKho(params);
    }

    @Override
    public Kho getKhoById(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("ID kho không hợp lệ");

        Kho kho = this.khoRepo.getKhoById(id);
        if (kho == null)
            throw new RuntimeException("Không tìm thấy kho với ID = " + id);

        return kho;
    }

    @Override
    public void addOrUpdateKho(Kho kho) {
        if (kho == null)
            throw new IllegalArgumentException("Thông tin kho không được null");

        if (kho.getDiaChi()== null || kho.getDiaChi().isBlank())
            throw new IllegalArgumentException("Địa chỉ kho không được để trống");

        this.khoRepo.addOrUpdateKho(kho);
    }

    @Override
    public void deleteKho(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("ID kho không hợp lệ");

        Kho kho = this.khoRepo.getKhoById(id);
        if (kho == null)
            throw new RuntimeException("Không tìm thấy kho để xóa");

        this.khoRepo.deleteKho(id);
    }
    
}
