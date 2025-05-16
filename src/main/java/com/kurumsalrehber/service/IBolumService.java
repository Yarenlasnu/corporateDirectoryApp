package com.kurumsalrehber.service;

import java.util.List;

import com.kurumsalrehber.dto.BolumDTO;
import com.kurumsalrehber.dto.FakulteDTO;
import com.kurumsalrehber.entity.Bolum;

public interface IBolumService {
	
	   BolumDTO save(BolumDTO dto);
	    List<BolumDTO> getAll();
	    List<BolumDTO> getByFakulteId(Long fakulteId);
	    void deleteById(Long id);
	    void bolumEkle(Long fakulteId, String ad);
	    void bolumSil(Long id);
	    List<Bolum> getTumBolumler();
	

}
