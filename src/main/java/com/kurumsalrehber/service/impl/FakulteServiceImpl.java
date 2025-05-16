package com.kurumsalrehber.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurumsalrehber.*;
import com.kurumsalrehber.dto.FakulteDTO;
import com.kurumsalrehber.entity.Fakulte;
import com.kurumsalrehber.repository.FakulteRepository;
import com.kurumsalrehber.service.IFakulteService;

@Service
public class FakulteServiceImpl implements IFakulteService{

    @Autowired
    private FakulteRepository fakulteRepository;
    
	@Override
	public FakulteDTO save(FakulteDTO dto) {
		Fakulte fakulte = new Fakulte();
        fakulte.setAd(dto.getAd());
        Fakulte saved = fakulteRepository.save(fakulte);
        dto.setId(saved.getId());
        return dto;
	}

	@Override
	public List<FakulteDTO> getAll() {
		return fakulteRepository.findAll().stream()
                .map(f -> {
                    FakulteDTO dto = new FakulteDTO();
                    dto.setId(f.getId());
                    dto.setAd(f.getAd());
                    return dto;
                }).collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long id) {
		fakulteRepository.deleteById(id);
		
	}

	 @Override
	    public void fakulteEkle(String ad) {
	        Fakulte f = new Fakulte();
	        f.setAd(ad);
	        fakulteRepository.save(f);
	    }

	    @Override
	    public void fakulteSil(Long id) {
	        fakulteRepository.deleteById(id);
	    }

	    @Override
	    public List<Fakulte> getTumFakulteler() {
	        return fakulteRepository.findAll();
	    }
}
