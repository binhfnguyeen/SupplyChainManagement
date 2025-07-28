/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Dell
 */
public class DonHangNhapRequest {
    private int idNhanVien;
    private int idKho;
    private int idVanChuyen;
    private int idNhaCungCap;
    private String tinhTrang;
    private Date thoiGianDuKien;
    private Date thoiGianNhan;
    private List<ChiTietDonHangNhapRequest> chiTietDonHangNhap;

    /**
     * @return the idNhanVien
     */
    public int getIdNhanVien() {
        return idNhanVien;
    }

    /**
     * @param idNhanVien the idNhanVien to set
     */
    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    /**
     * @return the idKho
     */
    public int getIdKho() {
        return idKho;
    }

    /**
     * @param idKho the idKho to set
     */
    public void setIdKho(int idKho) {
        this.idKho = idKho;
    }

    /**
     * @return the idVanChuyen
     */
    public int getIdVanChuyen() {
        return idVanChuyen;
    }

    /**
     * @param idVanChuyen the idVanChuyen to set
     */
    public void setIdVanChuyen(int idVanChuyen) {
        this.idVanChuyen = idVanChuyen;
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
     * @return the chiTietDonHangNhap
     */
    public List<ChiTietDonHangNhapRequest> getChiTietDonHangNhap() {
        return chiTietDonHangNhap;
    }

    /**
     * @param chiTietDonHangNhap the chiTietDonHangNhap to set
     */
    public void setChiTietDonHangNhap(List<ChiTietDonHangNhapRequest> chiTietDonHangNhap) {
        this.chiTietDonHangNhap = chiTietDonHangNhap;
    }

    /**
     * @return the idNhaCungCap
     */
    public int getIdNhaCungCap() {
        return idNhaCungCap;
    }

    /**
     * @param idNhaCungCap the idNhaCungCap to set
     */
    public void setIdNhaCungCap(int idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
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
}
