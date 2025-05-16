package com.kurumsalrehber.service;

import java.util.List;

import com.kurumsalrehber.dto.FakulteDTO;
import com.kurumsalrehber.entity.Fakulte;

public interface IFakulteService {
	FakulteDTO save(FakulteDTO dto);
    List<FakulteDTO> getAll();
    void deleteById(Long id);
    void fakulteEkle(String ad);
    void fakulteSil(Long id);
    List<Fakulte> getTumFakulteler();
}
