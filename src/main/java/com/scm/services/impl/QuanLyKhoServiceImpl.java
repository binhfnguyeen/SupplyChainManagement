/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Kho;
import com.scm.pojo.KhoSanpham;
import com.scm.pojo.Sanpham;
import com.scm.repositories.KhoRepository;
import com.scm.repositories.KhoSanPhamRepository;
import com.scm.repositories.SanPhamRepository;
import com.scm.services.QuanLyKhoService;
import java.util.Date;
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
    public void addSanPhamToKho(int khoId, int sanPhamId, int soLuong, Date hanSuDung) {
        if (soLuong <= 0) {
            throw new IllegalArgumentException("Số lượng sản phẩm phải lớn hơn 0");
        }

        if (hanSuDung == null || hanSuDung.before(new Date())) {
            throw new IllegalArgumentException("Hạn sử dụng phải là một ngày hợp lệ trong tương lai");
        }

        Kho kho = khoRepo.getKhoById(khoId);
        if (kho == null) {
            throw new IllegalArgumentException("Kho không tồn tại với ID = " + khoId);
        }

        Sanpham sp = spRepo.getSanPhamById(sanPhamId);
        if (sp == null) {
            throw new IllegalArgumentException("Sản phẩm không tồn tại với ID = " + sanPhamId);
        }

        KhoSanpham existed = khoSanphamRepo.findByKhoAndSanpham(khoId, sanPhamId);
        if (existed.getId() != null) {
            existed.setSoLuong(existed.getSoLuong() + soLuong);

            if (hanSuDung.after(existed.getHanSuDung())) {
                existed.setHanSuDung(hanSuDung);
            }

            khoSanphamRepo.updateKhoSanpham(existed);
        } else {

            KhoSanpham ksp = new KhoSanpham();
            ksp.setIDKho(kho);
            ksp.setIDSanPham(sp);
            ksp.setSoLuong(soLuong);
            ksp.setHanSuDung(hanSuDung);

            khoSanphamRepo.addKhoSanpham(ksp);
        }
    }

    @Override
    public List<KhoSanpham> getSanPhamTrongKho(int khoId) {
        Kho kho = khoRepo.getKhoById(khoId);
        if (kho == null) {
            throw new IllegalArgumentException("Kho không tồn tại");
        }

        return khoSanphamRepo.getKhoSanphamByKhoId(khoId);
    }

}
