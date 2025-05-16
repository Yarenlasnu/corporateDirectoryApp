package com.kurumsalrehber.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurumsalrehber.service.IBolumService;
import com.kurumsalrehber.dto.BolumDTO;
import com.kurumsalrehber.entity.Bolum;
import com.kurumsalrehber.entity.Fakulte;
import com.kurumsalrehber.repository.BolumRepository;
import com.kurumsalrehber.repository.FakulteRepository;

@Service
public class BolumServiceImpl implements IBolumService {
	
	
	    @Autowired
	    private BolumRepository bolumRepository;

	    @Autowired
	    private FakulteRepository fakulteRepository;

	    @Override
	    public BolumDTO save(BolumDTO dto) {
	        Bolum bolum = new Bolum();
	        bolum.setAd(dto.getAd());

	        Fakulte fakulte = fakulteRepository.findById(dto.getFakulteId())
	                .orElseThrow(() -> new RuntimeException("Fakülte bulunamadı"));

	        bolum.setFakulte(fakulte);
	        Bolum saved = bolumRepository.save(bolum);

	        dto.setId(saved.getId());
	        return dto;
	    }

	    @Override
	    public List<BolumDTO> getAll() {
	        return bolumRepository.findAll().stream().map(b -> {
	            BolumDTO dto = new BolumDTO();
	            dto.setId(b.getId());
	            dto.setAd(b.getAd());
	            dto.setFakulteId(b.getFakulte().getId());
	            return dto;
	        }).collect(Collectors.toList());
	    }

	    @Override
	    public List<BolumDTO> getByFakulteId(Long fakulteId) {
	        return bolumRepository.findAll().stream()
	                .filter(b -> b.getFakulte().getId().equals(fakulteId))
	                .map(b -> {
	                    BolumDTO dto = new BolumDTO();
	                    dto.setId(b.getId());
	                    dto.setAd(b.getAd());
	                    dto.setFakulteId(fakulteId);
	                    return dto;
	                }).collect(Collectors.toList());
	    }

	    @Override
	    public void deleteById(Long id) {
	        bolumRepository.deleteById(id);
	    }
	    
	    @Override
	    public void bolumEkle(Long fakulteId, String ad) {
	        Fakulte fakulte = fakulteRepository.findById(fakulteId).orElse(null);
	        if (fakulte != null) {
	            Bolum bolum = new Bolum();
	            bolum.setAd(ad);
	            bolum.setFakulte(fakulte);
	            bolumRepository.save(bolum);
	        }

	    }
	    

	    @Override
	    public void bolumSil(Long id) {
	        bolumRepository.deleteById(id);
	    }

	    @Override
	    public List<Bolum> getTumBolumler() {
	        return bolumRepository.findAll();
	    }
	}
	    



