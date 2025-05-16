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

import com.kurumsalrehber.dto.BolumDTO;
import com.kurumsalrehber.service.IBolumService;

@RestController
@RequestMapping("/api/bolum")
public class BolumController {

    private final IBolumService bolumService;

    @Autowired
    public BolumController(IBolumService bolumService) {
        this.bolumService = bolumService;
    }

    @PostMapping
    public BolumDTO bolumEkle(@RequestBody BolumDTO dto) {
        return bolumService.save(dto);
    }

    @GetMapping
    public List<BolumDTO> bolumleriListele() {
        return bolumService.getAll();
    }

    @GetMapping("/fakulte/{fakulteId}")
    public List<BolumDTO> fakulteyeGoreBolumler(@PathVariable Long fakulteId) {
        return bolumService.getByFakulteId(fakulteId);
    }

    @DeleteMapping("/{id}")
    public void bolumSil(@PathVariable Long id) {
        bolumService.deleteById(id);
    }
}