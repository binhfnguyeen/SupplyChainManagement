/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.Danhgia;
import com.scm.pojo.Nhacungcap;
import com.scm.repositories.DanhGiaRepository;
import com.scm.repositories.NhaCungCapRepository;
import com.scm.services.DanhGiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class DanhGiaServiceImpl implements DanhGiaService{
    
    @Autowired
    private DanhGiaRepository danhGiaRepo;

    @Autowired
    private NhaCungCapRepository nhaCungCapRepo;
    
    @Override
    public void addDanhGia(Danhgia dg) {
        if (dg.getChatLuong() == null || dg.getChatLuong() < 1 || dg.getChatLuong() > 5) {
            throw new IllegalArgumentException("Chất lượng phải từ 1 đến 5.");
        }

        if (dg.getGiaoHangDungHan() == null) {
            throw new IllegalArgumentException("Vui lòng chọn giao hàng đúng hạn hay không.");
        }

        if (dg.getIDNhaCungCap() == null || dg.getIDNhaCungCap().getId() == null) {
            throw new IllegalArgumentException("Nhà cung cấp không được để trống.");
        }

        Nhacungcap ncc = nhaCungCapRepo.getNCCById(dg.getIDNhaCungCap().getId());
        if (ncc == null) {
            throw new IllegalArgumentException("Nhà cung cấp không tồn tại.");
        }

        danhGiaRepo.addDanhGia(dg);
    }

    @Override
    public List<Danhgia> findDanhGiaByNCC(int nccId) {
        return danhGiaRepo.findDanhGiaByNCC(nccId);
    }
    
}
