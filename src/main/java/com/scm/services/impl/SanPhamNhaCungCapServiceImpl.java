/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.dto.SanphamNccDTO;
import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Sanpham;
import com.scm.pojo.SanphamNhacungcap;
import com.scm.repositories.NhaCungCapRepository;
import com.scm.repositories.SanPhamNhaCungCapRepository;
import com.scm.repositories.SanPhamRepository;
import com.scm.services.SanPhamNhaCungCapService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class SanPhamNhaCungCapServiceImpl implements SanPhamNhaCungCapService {

    @Autowired
    private SanPhamRepository sanphamRepository;

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    @Autowired
    private SanPhamNhaCungCapRepository spNCCRepository;

    @Override
    public void addSanPhamToNhaCungCap(int idNhaCungCap, int idSanPham, BigDecimal gia) {
        Sanpham sp = sanphamRepository.getSanPhamById(idSanPham);
        Nhacungcap ncc = nhaCungCapRepository.getNCCById(idNhaCungCap);

        if (sp == null) {
            throw new IllegalArgumentException("Sản phẩm với ID " + idSanPham + " không tồn tại!");
        }

        if (ncc == null) {
            throw new IllegalArgumentException("Nhà cung cấp với ID " + idNhaCungCap + " không tồn tại!");
        }

        if (gia == null || gia.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Giá phải lớn hơn 0!");
        }

        SanphamNhacungcap spncc = new SanphamNhacungcap();
        spncc.setGia(gia);
        spncc.setIDNhaCungCap(ncc);
        spncc.setIDSanPham(sp);

        spNCCRepository.add(spncc);
    }

    @Override
    public List<SanphamNhacungcap> getSanPhamCuaNhaCungCap(int idNhaCungCap) {
        Nhacungcap ncc = nhaCungCapRepository.getNCCById(idNhaCungCap);

        if (ncc == null) {
            throw new IllegalArgumentException("Nhà cung cấp không tồn tại");
        }

        return spNCCRepository.findByIDNhaCungCap(idNhaCungCap);
    }

    @Override
    public List<SanphamNccDTO> getAllSanPhamNhaCungCap(Map<String, String> params) {
        return this.spNCCRepository.getAllSanPhamNhaCungCap(params);
    }

    @Override
    public SanphamNhacungcap getSPNCCById(int id) {
        return this.spNCCRepository.getSPNCCById(id);
    }

    @Override
    public void addOrUpdate(SanphamNhacungcap spcc) {
        this.spNCCRepository.addOrUpdate(spcc);
    }

    @Override
    public void deleteSanPhamNhaCungCap(int id) {
        this.spNCCRepository.deleteSanPhamNhaCungCap(id);
    }

    @Override
    public BigDecimal getGia(Sanpham s, Nhacungcap ncc) {
        return this.spNCCRepository.getGia(s, ncc);
    }
}
