/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.scm.pojo.Khachhang;
import com.scm.pojo.Nhanvien;
import com.scm.pojo.User;
import com.scm.repositories.KhachHangRepository;
import com.scm.repositories.NhanVienRepository;
import com.scm.repositories.UserRepository;
import com.scm.services.UserService;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Dell
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NhanVienRepository nvRepository;

    @Autowired
    private KhachHangRepository khRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.getUserByUsername(username);
    }

    @Override
    public User addUser(Map<String, String> params, MultipartFile avatar) {
        User u = new User();
        u.setUsername(params.get("username"));
        u.setPassword(this.passwordEncoder.encode(params.get("password")));
        u.setRole(params.get("role"));

        if (!avatar.isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        this.userRepository.addUser(u);

        if ("KHACHHANG".equals(u.getRole())) {
            Khachhang kh = new Khachhang();
            kh.setTen(params.get("ten"));
            kh.setDiaChi(params.get("diaChi"));
            kh.setSdt(params.get("sdt"));
            kh.setThongTinLienHe(params.get("thongTinLienHe"));
            kh.setUserID(u);

            this.khRepository.add(kh);
            u.setKhachhang(kh);
        }

        return u;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        if (!u.getRole().equals("ADMIN"))
            throw new UsernameNotFoundException("Not authorized");
        
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + u.getRole()));

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    @PostConstruct
    public void createAdminUser() {
        try {
            if (userRepository.existedByUsername("admin") == false) {
                User u = new User();
                u.setUsername("admin");
                u.setPassword(this.passwordEncoder.encode("admin"));
                u.setRole("ADMIN");
                u.setAvatar("https://res.cloudinary.com/dwivkhh8t/image/upload/v1752911421/s17hqjuqfy2ao4z9umtn.jpg");

                userRepository.addUser(u);

                if ("ADMIN".equals(u.getRole())) {
                    Nhanvien nv = new Nhanvien();
                    nv.setHoTen("Admin SupChain");
                    nv.setChucVu("NHANVIEN_ADMIN");
                    nv.setUserID(u);

                    this.nvRepository.add(nv);
                    u.setNhanvien(nv);
                }

                System.out.println(">> Admin user created! (username: admin, password: admin)");
            } else {
                System.out.println(">> Admin user already exists.");
            }
        } catch (Exception e) {
            System.err.println(">> Error when creating admin user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        return this.userRepository.authenticate(username, password);
    }
    
}
