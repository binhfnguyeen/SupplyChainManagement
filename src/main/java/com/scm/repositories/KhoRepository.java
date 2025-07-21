/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.Kho;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dell
 */
public interface KhoRepository {
    List<Kho> getAllKho(Map<String, String> params);
    Kho getKhoById(int id);
    void addOrUpdateKho(Kho kho);
    void deleteKho(int id);
}
