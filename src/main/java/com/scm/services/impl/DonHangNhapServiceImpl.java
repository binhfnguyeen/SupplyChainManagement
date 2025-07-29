    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.dto.ChiTietDonHangNhapRequest;
import com.scm.dto.ChiTietDonHangNhapResponse;
import com.scm.dto.DonHangNhapRequest;
import com.scm.dto.DonHangNhapResponse;
import com.scm.pojo.Chitietdonhangnhap;
import com.scm.pojo.Donhangnhap;
import com.scm.pojo.Kho;
import com.scm.pojo.Nhacungcap;
import com.scm.pojo.Nhanvien;
import com.scm.pojo.Sanpham;
import com.scm.pojo.SanphamNhacungcap;
import com.scm.pojo.Vanchuyen;
import com.scm.repositories.ChiTietDonHangNhapRepository;
import com.scm.repositories.DonHangNhapRepository;
import com.scm.repositories.KhoRepository;
import com.scm.repositories.NhaCungCapRepository;
import com.scm.repositories.NhanVienRepository;
import com.scm.repositories.SanPhamNhaCungCapRepository;
import com.scm.repositories.SanPhamRepository;
import com.scm.repositories.VanChuyenRepository;
import com.scm.services.DonHangNhapService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class DonHangNhapServiceImpl implements DonHangNhapService {

    @Autowired
    private DonHangNhapRepository donHangNhapRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private KhoRepository khoRepository;

    @Autowired
    private VanChuyenRepository vanChuyenRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private ChiTietDonHangNhapRepository chitietRepository;

    @Autowired
    private SanPhamNhaCungCapRepository spnccRepository;

    @Autowired
    private NhaCungCapRepository nccRepository;

    @Override
    public void createDonHangNhap(DonHangNhapRequest request) {
        Nhanvien nv = this.nhanVienRepository.getNhanvienById(request.getIdNhanVien());
        Kho kho = this.khoRepository.getKhoById(request.getIdKho());
        Vanchuyen vc = this.vanChuyenRepository.getVanChuyenById(request.getIdVanChuyen());
        Nhacungcap ncc = this.nccRepository.getNCCById(request.getIdNhaCungCap());
        if (nv == null || kho == null || vc == null) {
            throw new IllegalArgumentException("Invalid NhanVien, Kho or VanChuyen ID");
        }

        Donhangnhap dhn = new Donhangnhap();
        dhn.setIDNhanVien(nv);
        dhn.setIDKho(kho);
        dhn.setIDVanChuyen(vc);
        dhn.setTinhTrang(request.getTinhTrang());
        dhn.setThoiGianDuKien(request.getThoiGianDuKien());
        dhn.setThoiGianNhan(request.getThoiGianNhan());

        donHangNhapRepository.addDonHangNhap(dhn);

        BigDecimal tongTien = BigDecimal.ZERO;

        for (ChiTietDonHangNhapRequest ct : request.getChiTietDonHangNhap()) {
            Sanpham sp = this.sanPhamRepository.getSanPhamById(ct.getIdSanPham());

            if (sp == null) {
                throw new IllegalArgumentException("Invalid SanPham ID: " + ct.getIdSanPham());
            }

            BigDecimal gia = this.spnccRepository.getGia(sp, ncc);

            BigDecimal thanhTien = gia.multiply(BigDecimal.valueOf(ct.getSoLuong()));
            tongTien = tongTien.add(thanhTien);

            Chitietdonhangnhap chitiet = new Chitietdonhangnhap();

            chitiet.setIDDonHang(dhn);
            chitiet.setIDSanPham(sp);
            chitiet.setSoLuong(ct.getSoLuong());

            chitietRepository.addChiTiet(chitiet);
        }

        if (vc.getSoTien() != null) {
            tongTien = tongTien.add(vc.getSoTien());
        }

        System.out.println(">>Tổng tiền: " + tongTien);
        dhn.setTongTien(tongTien);

        donHangNhapRepository.updateDonHangNhap(dhn);
    }

    @Override
    public List<DonHangNhapResponse> getAllDonHangNhap(Map<String, String> params) {
        List<Donhangnhap> ds = this.donHangNhapRepository.getDonHangNhap(params);
        List<DonHangNhapResponse> result = new ArrayList<>();

        for (Donhangnhap dh : ds) {
            DonHangNhapResponse res = new DonHangNhapResponse();
            res.setId(dh.getId());
            res.setTenNhanVien(dh.getIDNhanVien().getHoTen());
            res.setDiaChiKho(dh.getIDKho().getDiaChi());
            res.setTinhTrangVanChuyen(dh.getIDVanChuyen().getTinhTrang());
            res.setTinhTrang(dh.getTinhTrang());
            res.setTongTien(dh.getTongTien());
            res.setThoiGianDuKien(dh.getThoiGianDuKien());
            res.setThoiGianNhan(dh.getThoiGianNhan());

            List<ChiTietDonHangNhapResponse> listChiTiet = new ArrayList<>();
            for (Chitietdonhangnhap ct : dh.getChitietdonhangnhapSet()) {
                ChiTietDonHangNhapResponse ctRes = new ChiTietDonHangNhapResponse();
                ctRes.setIdSanPham(ct.getIDSanPham().getId());
                ctRes.setTenSanPham(ct.getIDSanPham().getTen());
                ctRes.setSoLuong(ct.getSoLuong());
                listChiTiet.add(ctRes);
            }

            res.setChiTiet(listChiTiet);
            result.add(res);
        }

        return result;
    }

    @Override
    public void updateDonHangNhap(DonHangNhapRequest request, int id) {
        Nhanvien nv = this.nhanVienRepository.getNhanvienById(request.getIdNhanVien());
        Kho kho = this.khoRepository.getKhoById(request.getIdKho());
        Vanchuyen vc = this.vanChuyenRepository.getVanChuyenById(request.getIdVanChuyen());
        Nhacungcap ncc = this.nccRepository.getNCCById(request.getIdNhaCungCap());
        if (nv == null || kho == null || vc == null) {
            throw new IllegalArgumentException("Invalid NhanVien, Kho or VanChuyen ID");
        }

        Donhangnhap dhn = donHangNhapRepository.getDonHangNhapById(id);
        dhn.setIDNhanVien(nv);
        dhn.setIDKho(kho);
        dhn.setIDVanChuyen(vc);
        dhn.setTinhTrang(request.getTinhTrang());
        dhn.setThoiGianDuKien(request.getThoiGianDuKien());
        dhn.setThoiGianNhan(request.getThoiGianNhan());

        this.chitietRepository.deleteChiTietByDonHangId(id);

        BigDecimal tongTien = BigDecimal.ZERO;

        for (ChiTietDonHangNhapRequest ct : request.getChiTietDonHangNhap()) {
            Sanpham sp = this.sanPhamRepository.getSanPhamById(ct.getIdSanPham());
            if (sp == null) {
                throw new IllegalArgumentException("Invalid SanPham ID: " + ct.getIdSanPham());
            }

            BigDecimal gia = this.spnccRepository.getGia(sp, ncc);
            BigDecimal thanhTien = gia.multiply(BigDecimal.valueOf(ct.getSoLuong()));
            tongTien = tongTien.add(thanhTien);

            Chitietdonhangnhap chitiet = new Chitietdonhangnhap();
            chitiet.setIDDonHang(dhn);
            chitiet.setIDSanPham(sp);
            chitiet.setSoLuong(ct.getSoLuong());
            chitietRepository.addChiTiet(chitiet);
        }

        if (vc.getSoTien() != null) {
            tongTien = tongTien.add(vc.getSoTien());
        }

        dhn.setTongTien(tongTien);
        donHangNhapRepository.updateDonHangNhap(dhn);
    }

    @Override
    public List<Donhangnhap> getAllDonHangNhap() {
        return this.donHangNhapRepository.getDonHangNhap(null);
    }

    @Override
    public Donhangnhap getDonHangNhapById(int id) {
        return this.donHangNhapRepository.getDonHangNhapById(id);
    }

    @Override
    public void addOrUpdateDonHangNhap(Donhangnhap dhn) {
        if (dhn.getId() == null) {
            this.donHangNhapRepository.addDonHangNhap(dhn);
        } else {
            this.donHangNhapRepository.updateDonHangNhap(dhn);
        }
    }

    @Override
    public void deleteDonHangNhap(int id) {
        this.donHangNhapRepository.deleteDonHangNhap(id);
    }

    @Override
    public void updateTongTienHang(int idDonHang) {
        Donhangnhap dh = this.getDonHangNhapById(idDonHang);
        List<Chitietdonhangnhap> dsChiTiet = chitietRepository.findByIdDonHang(idDonHang);
        BigDecimal tongTien = BigDecimal.ZERO;

        for (Chitietdonhangnhap ct : dsChiTiet) {
            Sanpham sp = this.sanPhamRepository.getSanPhamById(ct.getIDSanPham().getId());
            SanphamNhacungcap spncc = this.spnccRepository.findBySanpham(sp);
            BigDecimal gia = spncc.getGia();
            BigDecimal thanhTien = gia.multiply(BigDecimal.valueOf(ct.getSoLuong()));
            tongTien = tongTien.add(thanhTien);
        }

        dh.setTongTien(tongTien);
        this.donHangNhapRepository.updateDonHangNhap(dh);
    }

}
