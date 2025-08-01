/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Kho;
import com.scm.pojo.KhoSanpham;
import com.scm.repositories.KhoRepository;
import com.scm.repositories.KhoSanPhamRepository;
import com.scm.repositories.SanPhamRepository;
import com.scm.services.QuanLyKhoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class QuanLyKhoServiceImpl implements QuanLyKhoService {

    @Autowired
    private KhoRepository khoRepo;

    @Autowired
    private KhoSanPhamRepository khoSanphamRepo;

    @Autowired
    private SanPhamRepository spRepo;

    @Override
    public List<KhoSanpham> getSanPhamTrongKho(int khoId) {
        Kho kho = khoRepo.getKhoById(khoId);
        if (kho == null) {
            throw new IllegalArgumentException("Kho không tồn tại");
        }

        return khoSanphamRepo.getKhoSanphamByKhoId(khoId);
    }

}
