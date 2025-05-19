package com.kurumsalrehber.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kurumsalrehber.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}

