package com.kurumsalrehber.service;

import java.util.List;

import com.kurumsalrehber.dto.PersonelDTO;
import com.kurumsalrehber.entity.Personel;

public interface IPersonelService {
	
	 PersonelDTO save(PersonelDTO dto);
	    List<PersonelDTO> getAll();
	    List<PersonelDTO> getByBolumId(Long bolumId);
	    void deleteById(Long id);
	    List<PersonelDTO> search(String keyword);
	    void personelEkle(Long fakulteId, Long bolumId, String ad, String soyad, String telefon);
	    void personelSil(Long id);
	    List<Personel> getTumPersoneller();
}
