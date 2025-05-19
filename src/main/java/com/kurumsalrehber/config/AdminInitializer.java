package com.kurumsalrehber.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.kurumsalrehber.entity.Admin;
import com.kurumsalrehber.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    private static final Logger logger = LoggerFactory.getLogger(AdminInitializer.class);


    @Override
    public void run(String... args) {
        if (adminRepository.findByUsername("admin").isEmpty()) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("1234Admin*")); // Şifreyi hashle
            adminRepository.save(admin);

            logger.info("✅ Varsayılan admin başarıyla veritabanına eklendi.");
        } else {
            logger.info("ℹ️  Admin zaten veritabanında mevcut. Eklenmedi.");
        }
    }

}
