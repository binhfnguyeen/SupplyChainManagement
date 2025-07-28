/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.dto.HoaDonNhapResponse;
import com.scm.pojo.Chitietdonhangnhap;
import com.scm.pojo.Donhangnhap;
import com.scm.pojo.Hoadonnhap;
import com.scm.pojo.Kho;
import com.scm.pojo.KhoSanpham;
import com.scm.pojo.Sanpham;
import com.scm.repositories.DonHangNhapRepository;
import com.scm.repositories.HoaDonNhapRepository;
import com.scm.repositories.KhoSanPhamRepository;
import com.scm.services.HoaDonNhapService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class HoaDonNhapServiceImpl implements HoaDonNhapService {

    @Autowired
    private HoaDonNhapRepository hoaDonNhapRepository;

    @Autowired
    private DonHangNhapRepository donHangNhapRepository;

    @Autowired
    private KhoSanPhamRepository kspRepository;

    @Override
    public void xuatHoaDonNhap(int id) {
        Donhangnhap dhn = this.donHangNhapRepository.getDonHangNhapById(id);

        if (dhn == null) {
            throw new IllegalArgumentException("Invalid Don Hang Nhap ID: " + dhn.getId());
        }

        Hoadonnhap hdn = new Hoadonnhap();
        hdn.setIDDonHang(dhn);
        hdn.setTongChiPhi(dhn.getTongTien());

        this.hoaDonNhapRepository.addOrUpdateHoaDonNhap(hdn);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, 1);

        Kho kho = dhn.getIDKho();
        for (Chitietdonhangnhap ct : dhn.getChitietdonhangnhapSet()) {
            Sanpham sp = ct.getIDSanPham();

            KhoSanpham ksp = this.kspRepository.findByKhoAndSanpham(kho.getId(), sp.getId());

            if (ksp != null) {
                int newSoLuong = ksp.getSoLuong() + ct.getSoLuong();
                ksp.setSoLuong(newSoLuong);
                ksp.setHanSuDung(cal.getTime());
                
                this.kspRepository.updateKhoSanpham(ksp);
            } else {
                KhoSanpham newKsp = new KhoSanpham();

                newKsp.setIDKho(kho);
                newKsp.setIDSanPham(sp);
                newKsp.setSoLuong(ct.getSoLuong());
                newKsp.setHanSuDung(cal.getTime());
                
                this.kspRepository.addKhoSanpham(newKsp);
            }
        }
    }

    @Override
    public List<HoaDonNhapResponse> getAllHoaDonNhap(Map<String, String> params) {
        List<Hoadonnhap> ds = this.hoaDonNhapRepository.getAllHoaDonNhap(params);
        List<HoaDonNhapResponse> result = new ArrayList<>();

        for (Hoadonnhap hd : ds) {
            HoaDonNhapResponse res = new HoaDonNhapResponse();
            res.setId(hd.getId());
            res.setIdDonHang(hd.getIDDonHang().getId());
            res.setTongChiPhi(hd.getTongChiPhi());

            result.add(res);
        }
        return result;
    }

    @Override
    public HoaDonNhapResponse getHoaDonNhapById(int id) {
        Hoadonnhap hd = this.hoaDonNhapRepository.getHoaDonNhapById(id);
        HoaDonNhapResponse res = new HoaDonNhapResponse();
        res.setId(hd.getId());
        res.setIdDonHang(hd.getIDDonHang().getId());
        res.setTongChiPhi(hd.getTongChiPhi());

        return res;
    }

    @Override
    public void addOrUpdateHoaDonNhap(Hoadonnhap hdn) {
        this.hoaDonNhapRepository.addOrUpdateHoaDonNhap(hdn);
    }

    @Override
    public List<Hoadonnhap> getDsHoaDon(Map<String, String> params) {
        return this.hoaDonNhapRepository.getAllHoaDonNhap(params);
    }

    @Override
    public Hoadonnhap getHoaDonById(int id) {
        return this.hoaDonNhapRepository.getHoaDonNhapById(id);
    }

    @Override
    public void deleteHoaDonNhap(int id) {
       this.hoaDonNhapRepository.deleteHoaDonNhap(id);
    }

}
