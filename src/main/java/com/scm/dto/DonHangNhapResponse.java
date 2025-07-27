/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dell
 */
public class DonHangNhapResponse {
    private int id;
    private String tenNhanVien;
    private String diaChiKho;
    private String tinhTrangVanChuyen;
    private String tinhTrang;
    private BigDecimal tongTien;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date thoiGianDuKien;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date thoiGianNhan;
    private List<ChiTietDonHangNhapResponse> chiTiet;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tinhTrang
     */
    public String getTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param tinhTrang the tinhTrang to set
     */
    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    /**
     * @return the tongTien
     */
    public BigDecimal getTongTien() {
        return tongTien;
    }

    /**
     * @param tongTien the tongTien to set
     */
    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    /**
     * @return the thoiGianDuKien
     */
    public Date getThoiGianDuKien() {
        return thoiGianDuKien;
    }

    /**
     * @param thoiGianDuKien the thoiGianDuKien to set
     */
    public void setThoiGianDuKien(Date thoiGianDuKien) {
        this.thoiGianDuKien = thoiGianDuKien;
    }

    /**
     * @return the thoiGianNhan
     */
    public Date getThoiGianNhan() {
        return thoiGianNhan;
    }

    /**
     * @param thoiGianNhan the thoiGianNhan to set
     */
    public void setThoiGianNhan(Date thoiGianNhan) {
        this.thoiGianNhan = thoiGianNhan;
    }

    /**
     * @return the chiTiet
     */
    public List<ChiTietDonHangNhapResponse> getChiTiet() {
        return chiTiet;
    }

    /**
     * @param chiTiet the chiTiet to set
     */
    public void setChiTiet(List<ChiTietDonHangNhapResponse> chiTiet) {
        this.chiTiet = chiTiet;
    }

    /**
     * @return the tenNhanVien
     */
    public String getTenNhanVien() {
        return tenNhanVien;
    }

    /**
     * @param tenNhanVien the tenNhanVien to set
     */
    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    /**
     * @return the diaChiKho
     */
    public String getDiaChiKho() {
        return diaChiKho;
    }

    /**
     * @param diaChiKho the diaChiKho to set
     */
    public void setDiaChiKho(String diaChiKho) {
        this.diaChiKho = diaChiKho;
    }

    /**
     * @return the tinhTrangVanChuyen
     */
    public String getTinhTrangVanChuyen() {
        return tinhTrangVanChuyen;
    }

    /**
     * @param tinhTrangVanChuyen the tinhTrangVanChuyen to set
     */
    public void setTinhTrangVanChuyen(String tinhTrangVanChuyen) {
        this.tinhTrangVanChuyen = tinhTrangVanChuyen;
    }
}
