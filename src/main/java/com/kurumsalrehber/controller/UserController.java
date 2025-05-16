package com.kurumsalrehber.controller;

import com.kurumsalrehber.dto.PersonelDTO;
import com.kurumsalrehber.service.IPersonelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IPersonelService personelService;

    @GetMapping("/kullanici/panel")
    public String kullaniciPaneli(Model model) {
        return "kullaniciPanel"; // <<< burası da "user/kullaniciPanel" değil
    }

    @GetMapping("/kullanici/ara")
    public String kullaniciAra(@RequestParam("arama") String arama, Model model) {
        List<PersonelDTO> sonucListesi = personelService.search(arama);
        model.addAttribute("sonucListesi", sonucListesi);
        return "kullaniciPanel";
    }


}
