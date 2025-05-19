package com.kurumsalrehber.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurumsalrehber.entity.Admin;
import com.kurumsalrehber.repository.AdminRepository;

import lombok.Data;

@Service
@Data
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> getByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public void updatePassword(Admin admin, String newEncodedPassword) {
        admin.setPassword(newEncodedPassword);
        adminRepository.save(admin);
    }
}