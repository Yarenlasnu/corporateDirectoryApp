package com.kurumsalrehber.controller;

import com.kurumsalrehber.dto.PersonelDTO;
import com.kurumsalrehber.service.IPersonelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IPersonelService personelService;

    @GetMapping("/kullanici/panel")
    public String kullaniciPaneli(Model model) {
        logger.info("Kullanıcı paneli görüntülendi.");
        return "kullaniciPanel";
    }

    @GetMapping("/kullanici/ara")
    public String kullaniciAra(@RequestParam(value = "arama", required = false) String arama, Model model) {
        List<PersonelDTO> sonucListesi = null;

        if (arama != null && !arama.trim().isEmpty()) {
            logger.info("Kullanıcı arama yaptı: {}", arama);
            sonucListesi = personelService.search(arama);
        } else {
            logger.warn("Boş veya geçersiz arama parametresi alındı.");
        }

        model.addAttribute("sonucListesi", sonucListesi);
        return "kullaniciPanel";
    }
}
