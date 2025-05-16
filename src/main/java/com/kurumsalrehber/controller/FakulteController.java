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

import com.kurumsalrehber.dto.FakulteDTO;
import com.kurumsalrehber.service.IFakulteService;

@RestController
@RequestMapping("/api/fakulte")
public class FakulteController {

    @Autowired
    private IFakulteService fakulteService;

    @PostMapping
    public FakulteDTO fakulteEkle(@RequestBody FakulteDTO dto) {
        return fakulteService.save(dto);
    }

    @GetMapping
    public List<FakulteDTO> fakulteListele() {
        return fakulteService.getAll();
    }

    @DeleteMapping("/{id}")
    public void fakulteSil(@PathVariable Long id) {
        fakulteService.deleteById(id);
    }
}