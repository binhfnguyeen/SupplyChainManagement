/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.KhoSanpham;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface KhoSanPhamRepository {
    void addKhoSanpham(KhoSanpham ksp);
    List<KhoSanpham> getKhoSanphamByKhoId(int khoId);
    KhoSanpham findByKhoAndSanpham(int khoId, int sanPhamId);
    void updateKhoSanpham(KhoSanpham ksp);
}
