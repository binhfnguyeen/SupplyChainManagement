/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Nhacungcap;
import com.scm.repositories.NhaCungCapRepository;
import com.scm.services.NhaCungCapService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    @Override
    public List<Nhacungcap> getDsNhaCungCap(Map<String, String> params) {
        return this.nhaCungCapRepository.getDsNhaCungCap(params);
    }

    @Override
    public Nhacungcap getNCCById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID không hợp lệ");
        }

        Nhacungcap ncc = this.nhaCungCapRepository.getNCCById(id);
        if (ncc == null) {
            throw new RuntimeException("Không tìm thấy nhà cung cấp với ID = " + id);
        }

        return ncc;
    }

    @Override
    public void addOrUpdateNCC(Nhacungcap ncc) {
        if (ncc == null) {
            throw new IllegalArgumentException("Nhà cung cấp không được null");
        }

        if (ncc.getTen() == null || ncc.getTen().isBlank()) {
            throw new IllegalArgumentException("Tên nhà cung cấp không được bỏ trống");
        }

        this.nhaCungCapRepository.addOrUpdateNCC(ncc);
    }

    @Override
    public void deleteNCC(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID không hợp lệ");
        }

        Nhacungcap ncc = this.nhaCungCapRepository.getNCCById(id);
        if (ncc == null) {
            throw new RuntimeException("Không tìm thấy nhà cung cấp với ID = " + id);
        }

        this.nhaCungCapRepository.deleteNCC(id);
    }

}
