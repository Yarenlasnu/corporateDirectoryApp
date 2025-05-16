package com.kurumsalrehber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kurumsalrehber.service.IBolumService;
import com.kurumsalrehber.service.IFakulteService;
import com.kurumsalrehber.service.IPersonelService;

import jakarta.servlet.http.HttpSession;


import com.kurumsalrehber.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IFakulteService fakulteService;

    @Autowired
    private IBolumService bolumService;

    @Autowired
    private IPersonelService personelService;

    @GetMapping("/login")
    public String loginSayfasi() {
        return "login"; // Eğer JSP'nin yolu /WEB-INF/views/login.jsp ise
    }

    @PostMapping("/login")
    public String loginKontrol(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session) {
        if ("admin".equals(username) && "1234".equals(password)) {
            session.setAttribute("admin", true);
            return "redirect:/admin/panel";
        } else {
            return "redirect:/admin/login?error=true";
        }
    }
    
    @PostMapping("/fakulte/ekle")
    public String fakulteEkle(@RequestParam String ad) {
        fakulteService.fakulteEkle(ad);
        return "redirect:/admin/panel";
    }

    @PostMapping("/bolum/ekle")
    public String bolumEkle(@RequestParam Long fakulteId, @RequestParam String ad) {
        bolumService.bolumEkle(fakulteId, ad);
        return "redirect:/admin/panel";
    }

    @PostMapping("/personel/ekle")
    public String personelEkle(
        @RequestParam Long fakulteId,
        @RequestParam Long bolumId,
        @RequestParam String ad,
        @RequestParam String soyad,
        @RequestParam String telefon
    ) {
        personelService.personelEkle(fakulteId, bolumId, ad, soyad, telefon);
        return "redirect:/admin/panel";
    }

    @PostMapping("/fakulte/sil")
    public String fakulteSil(@RequestParam Long id) {
        fakulteService.fakulteSil(id);
        return "redirect:/admin/panel";
    }

    @PostMapping("/bolum/sil")
    public String bolumSil(@RequestParam Long id) {
        bolumService.bolumSil(id);
        return "redirect:/admin/panel";
    }

    @PostMapping("/personel/sil")
    public String personelSil(@RequestParam Long id) {
        personelService.personelSil(id);
        return "redirect:/admin/panel";
    }


    @GetMapping("/panel")
    public String getAdminPanel(Model model, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login"; // << DOĞRUSU BU
        }

        model.addAttribute("fakulteListesi", fakulteService.getAll());
        model.addAttribute("bolumListesi", bolumService.getAll());
        model.addAttribute("personelListesi", personelService.getTumPersoneller()); // ← EKLE

        return "adminPanel";
    }


}
