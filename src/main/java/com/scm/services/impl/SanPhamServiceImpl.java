/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Sanpham;
import com.scm.repositories.SanPhamRepository;
import com.scm.services.SanPhamService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository spRepo;

    @Override
    public Sanpham getSanPhamById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID sản phẩm không hợp lệ");
        }

        Sanpham sp = this.spRepo.getSanPhamById(id);
        if (sp == null) {
            throw new RuntimeException("Không tìm thấy sản phẩm với ID = " + id);
        }

        return sp;
    }

    @Override
    public void addOrUpdateSanpham(Sanpham sp) {
        if (sp == null) {
            throw new IllegalArgumentException("Thông tin sản phẩm không được null");
        }

        if (sp.getTen() == null || sp.getTen().isBlank()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống");
        }
        this.spRepo.addOrUpdateSanpham(sp);
    }

    @Override
    public List<Sanpham> getAllSanpham(Map<String, String> params) {
        return this.spRepo.getAllSanpham(params);
    }

    @Override
    public void deleteSanpham(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID sản phẩm không hợp lệ.");
        }

        Sanpham sp = this.spRepo.getSanPhamById(id);
        if (sp == null) {
            throw new RuntimeException("Không tìm thấy sản phẩm với ID = " + id);
        }

        this.spRepo.deleteSanpham(id);
    }

}
