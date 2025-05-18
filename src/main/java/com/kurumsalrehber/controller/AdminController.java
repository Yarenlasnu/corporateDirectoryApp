package com.kurumsalrehber.controller;

import com.kurumsalrehber.service.IBolumService;
import com.kurumsalrehber.service.IFakulteService;
import com.kurumsalrehber.service.IPersonelService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private IFakulteService fakulteService;

    @Autowired
    private IBolumService bolumService;

    @Autowired
    private IPersonelService personelService;

    @GetMapping("/login")
    public String loginSayfasi() {
        logger.info("Login sayfası görüntüleniyor.");
        return "login";
    }

    @PostMapping("/login")
    public String loginKontrol(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session) {
        logger.info("Giriş denemesi: Kullanıcı adı = {}", username);
        if ("admin".equals(username) && "1234".equals(password)) {
            session.setAttribute("admin", true);
            logger.info("Giriş başarılı.");
            return "redirect:/admin/panel";
        } else {
            logger.warn("Geçersiz giriş denemesi.");
            return "redirect:/admin/login?error=true";
        }
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
