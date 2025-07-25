/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Donhangnhap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface DonHangNhapRepository {
    void addDonHangNhap(Donhangnhap dhn);
    List<Donhangnhap> getDonHangNhap(Map<String, String> params);
    Donhangnhap getDonHangNhapById(int id);
    void updateDonHangNhap(Donhangnhap dhn);
    void deleteDonHangNhap(int id);
}
