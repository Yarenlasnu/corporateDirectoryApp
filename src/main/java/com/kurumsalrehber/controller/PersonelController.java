package com.kurumsalrehber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kurumsalrehber.service.IPersonelService;
import com.kurumsalrehber.dto.PersonelDTO;

@RestController
@RequestMapping("/api/personel")
public class PersonelController {

    @Autowired
    private IPersonelService personelService;

    @PostMapping
    public PersonelDTO personelEkle(@RequestBody PersonelDTO dto) {
        return personelService.save(dto);
    }

    @GetMapping
    public List<PersonelDTO> personelListele() {
        return personelService.getAll();
    }

    @GetMapping("/bolum/{bolumId}")
    public List<PersonelDTO> bolumeGorePersonelListele(@PathVariable Long bolumId) {
        return personelService.getByBolumId(bolumId);
    }

    @DeleteMapping("/{id}")
    public void personelSil(@PathVariable Long id) {
        personelService.deleteById(id);
    }
}