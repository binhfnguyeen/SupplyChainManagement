/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Nhacungcap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface NhaCungCapRepository {
    List<Nhacungcap> getDsNhaCungCap(Map<String, String> params);
    Nhacungcap getNCCById(int id);
    void addOrUpdateNCC(Nhacungcap ncc);
    void deleteNCC(int id);
}
