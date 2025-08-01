/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.dto.DonHangNhapRequest;
import com.scm.dto.DonHangNhapResponse;
import com.scm.pojo.Donhangnhap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface DonHangNhapService {
    void createDonHangNhap(DonHangNhapRequest request);
    List<DonHangNhapResponse> getAllDonHangNhap(Map<String, String> params);
    void updateDonHangNhap(DonHangNhapRequest request, int id);
    List<Donhangnhap> getAllDonHangNhap();
    Donhangnhap getDonHangNhapById(int id);
    void addOrUpdateDonHangNhap(Donhangnhap dhn);
    void deleteDonHangNhap(int id);
    void updateTongTienHang(int idDonHang);
}
