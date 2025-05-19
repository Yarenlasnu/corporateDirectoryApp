package com.kurumsalrehber.controller;

import com.kurumsalrehber.entity.Admin;
import com.kurumsalrehber.service.IBolumService;
import com.kurumsalrehber.service.IFakulteService;
import com.kurumsalrehber.service.IPersonelService;
import com.kurumsalrehber.service.impl.AdminService;

import jakarta.servlet.http.HttpSession;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IFakulteService fakulteService;

    @Autowired
    private IBolumService bolumService;

    @Autowired
    private IPersonelService personelService;
    
    @Autowired
    private AdminService adminService;
    
    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    
    @GetMapping("/login")
    public String loginSayfasi() {
        logger.info("Login sayfası görüntüleniyor.");
        return "login";
    }
    
    private boolean isPasswordStrong(String password) {
        return password != null &&
               password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*].*");
    }

    @PostMapping("/login")
    public String loginKontrol(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session) {

        logger.info("Giriş denemesi: {}", username);

        Optional<Admin> adminOpt = adminService.getByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (passwordEncoder.matches(password, admin.getPassword())) {
                session.setAttribute("admin", true);
                logger.info("Giriş başarılı.");
                return "redirect:/admin/panel";
            }
        }

        logger.warn("Geçersiz giriş.");
        return "redirect:/admin/login?error=true";
    }


    @GetMapping("/change-password")
    public String showChangePasswordPage() {
        return "changePassword";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 HttpSession session,
                                 Model model) {

        Optional<Admin> adminOpt = adminService.getByUsername("admin");
        if (adminOpt.isEmpty()) {
            model.addAttribute("error", "Admin bulunamadı.");
            return "changePassword";
        }

        Admin admin = adminOpt.get();

        // Mevcut şifre kontrolü
        if (!passwordEncoder.matches(currentPassword, admin.getPassword())) {
            model.addAttribute("error", "Mevcut şifre hatalı.");
            return "changePassword";
        }

        // Yeni şifre güvenli mi?
        if (!isPasswordStrong(newPassword)) {
            model.addAttribute("error", "Yeni şifre zayıf. En az 8 karakter, büyük harf, küçük harf, rakam ve özel karakter içermelidir.");
            return "changePassword";
        }

        // Yeni şifreler aynı mı?
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Yeni şifreler eşleşmiyor.");
            return "changePassword";
        }

        // Şifre güncelleme
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        adminService.updatePassword(admin, encodedNewPassword);

        model.addAttribute("success", "Şifreniz başarıyla güncellendi.");
        return "changePassword";
    }

    @GetMapping("/panel")
    public String getAdminPanel(@RequestParam(value = "fakulteId", required = false) Long fakulteId,
                                @RequestParam(value = "seciliBolumId", required = false) Long bolumId,
                                Model model,
                                HttpSession session) {
        if (session.getAttribute("admin") == null) {
            logger.warn("Yetkisiz erişim denemesi: panel");
            return "redirect:/admin/login";
        }

        model.addAttribute("fakulteListesi", fakulteService.getAll());
        model.addAttribute("bolumListesi", bolumService.getAll());
        model.addAttribute("personelListesi", personelService.getTumPersoneller());

        if (fakulteId != null) {
            logger.info("Panel açıldı - Seçili Fakülte ID: {}", fakulteId);
            model.addAttribute("seciliFakulteId", fakulteId);
        }

        if (bolumId != null) {
            logger.info("Panel açıldı - Seçili Bölüm ID: {}", bolumId);
            model.addAttribute("seciliBolumId", bolumId);
        }

        return "adminPanel";
    }

    @PostMapping("/fakulte/ekle")
    public String fakulteEkle(@RequestParam String ad) {
        logger.info("Yeni fakülte ekleniyor: {}", ad);
        fakulteService.fakulteEkle(ad);
        return "redirect:/admin/panel";
    }

    @PostMapping("/bolum/ekle")
    public String bolumEkle(@RequestParam Long fakulteId, @RequestParam String ad) {
        logger.info("Yeni bölüm ekleniyor: {} (Fakülte ID: {})", ad, fakulteId);
        bolumService.bolumEkle(fakulteId, ad);
        return "redirect:/admin/panel?fakulteId=" + fakulteId;
    }

    @PostMapping("/personel/ekle")
    public String personelEkle(@RequestParam Long fakulteId,
                               @RequestParam Long bolumId,
                               @RequestParam String ad,
                               @RequestParam String soyad,
                               @RequestParam String telefon) {
        logger.info("Yeni personel ekleniyor: {} {} - Tel: {}", ad, soyad, telefon);
        personelService.personelEkle(fakulteId, bolumId, ad, soyad, telefon);
        return "redirect:/admin/panel?fakulteId=" + fakulteId + "&seciliBolumId=" + bolumId;
    }

    @PostMapping("/fakulte/sil")
    public String fakulteSil(@RequestParam Long id) {
        logger.warn("Fakülte siliniyor: ID = {}", id);
        fakulteService.fakulteSil(id);
        return "redirect:/admin/panel";
    }

    @PostMapping("/bolum/sil")
    public String bolumSil(@RequestParam Long id,
                           @RequestParam Long fakulteId) {
        logger.warn("Bölüm siliniyor: ID = {}", id);
        bolumService.bolumSil(id);
        return "redirect:/admin/panel?fakulteId=" + fakulteId;
    }

    @PostMapping("/personel/sil")
    public String personelSil(@RequestParam Long id,
                              @RequestParam Long fakulteId,
                              @RequestParam Long bolumId) {
        logger.warn("Personel siliniyor: ID = {}", id);
        personelService.personelSil(id);
        return "redirect:/admin/panel?fakulteId=" + fakulteId + "&seciliBolumId=" + bolumId;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        logger.info("Oturum sonlandırıldı.");
        return "redirect:/admin/login";
    }
}
