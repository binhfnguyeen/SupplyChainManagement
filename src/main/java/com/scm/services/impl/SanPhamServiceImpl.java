/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.scm.pojo.Sanpham;
import com.scm.repositories.SanPhamRepository;
import com.scm.services.SanPhamService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Dell
 */
@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository spRepo;

    @Autowired
    private Cloudinary cloudinary;

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
    public void addOrUpdateSanpham(Sanpham sp, MultipartFile hinh) {
        if (!hinh.isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(hinh.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                sp.setHinh(res.get("secure_url").toString());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        spRepo.addOrUpdateSanpham(sp);
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
