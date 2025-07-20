/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.scm.pojo.Nhanvien;
import com.scm.repositories.NhanVienRepository;
import com.scm.services.NhanVienService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service
public class NhanVienServiceImpl implements NhanVienService{
    @Autowired
    private NhanVienRepository nvRepository;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Override
    public List<Nhanvien> getDsNhanVien(Map<String, String> params) {
        return this.nvRepository.getDsNhanVien(params);
    }

    @Override
    public void deleteNhanVien(Integer id) {
        this.nvRepository.deleteNhanVien(id);
    }

    @Override
    public void addOrUpdateNhanvienWithUser(Nhanvien nv) {
        if (!nv.getUserID().getFile().isEmpty()){
            try {
                Map res = cloudinary.uploader().upload(nv.getUserID().getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                nv.getUserID().setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(NhanVienServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.nvRepository.addOrUpdateNhanvienWithUser(nv);
    }

    @Override
    public Nhanvien getNhanvienById(int id) {
        return this.nvRepository.getNhanvienById(id);
    }
    
}
