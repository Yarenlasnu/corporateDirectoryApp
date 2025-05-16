package com.kurumsalrehber.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurumsalrehber.dto.PersonelDTO;
import com.kurumsalrehber.entity.Bolum;
import com.kurumsalrehber.entity.Fakulte;
import com.kurumsalrehber.entity.Personel;
import com.kurumsalrehber.repository.BolumRepository;
import com.kurumsalrehber.repository.FakulteRepository;
import com.kurumsalrehber.repository.PersonelRepository;
import com.kurumsalrehber.service.IPersonelService;

@Service
public class PersonelServiceImpl implements IPersonelService {

    @Autowired
    private PersonelRepository personelRepository;

    @Autowired
    private BolumRepository bolumRepository;
    
    @Autowired
    private FakulteRepository fakulteRepository;

    @Override
    public PersonelDTO save(PersonelDTO dto) {
        Personel personel = new Personel();
        personel.setAd(dto.getAd());
        personel.setSoyad(dto.getSoyad());
        personel.setTelefon(dto.getTelefon());

        Bolum bolum = bolumRepository.findById(dto.getBolumId())
                .orElseThrow(() -> new RuntimeException("Bölüm bulunamadı"));

        personel.setBolum(bolum);
        Personel saved = personelRepository.save(personel);

        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public List<PersonelDTO> getAll() {
        return personelRepository.findAll().stream().map(p -> {
            PersonelDTO dto = new PersonelDTO();
            dto.setId(p.getId());
            dto.setAd(p.getAd());
            dto.setSoyad(p.getSoyad());
            dto.setTelefon(p.getTelefon());
            dto.setBolumId(p.getBolum().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PersonelDTO> getByBolumId(Long bolumId) {
        return personelRepository.findAll().stream()
                .filter(p -> p.getBolum().getId().equals(bolumId))
                .map(p -> {
                    PersonelDTO dto = new PersonelDTO();
                    dto.setId(p.getId());
                    dto.setAd(p.getAd());
                    dto.setSoyad(p.getSoyad());
                    dto.setTelefon(p.getTelefon());
                    dto.setBolumId(bolumId);
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        personelRepository.deleteById(id);
    }
    
    @Override
    public List<PersonelDTO> search(String arama) {
        return personelRepository.search(arama);
    }

    
    private PersonelDTO convertToDTO(Personel personel) {
        PersonelDTO dto = new PersonelDTO();
        dto.setId(personel.getId());
        dto.setAd(personel.getAd());
        dto.setSoyad(personel.getSoyad());
        dto.setTelefon(personel.getTelefon());
        dto.setBolumId(personel.getBolum().getId());
        dto.setBolumAd(personel.getBolum().getAd());
        dto.setFakulteAd(personel.getBolum().getFakulte().getAd());
        return dto;
    }
    
    @Override
    public void personelSil(Long id) {
        personelRepository.deleteById(id);
    }

    @Override
    public List<Personel> getTumPersoneller() {
        return personelRepository.findAll();
    }
    
    @Override
    public void personelEkle(Long fakulteId, Long bolumId, String ad, String soyad, String telefon) {
        Personel personel = new Personel();
        personel.setAd(ad);
        personel.setSoyad(soyad);
        personel.setTelefon(telefon);
        
        Fakulte fakulte = fakulteRepository.findById(fakulteId).orElse(null);
        Bolum bolum = bolumRepository.findById(bolumId).orElse(null);

        personel.setFakulte(fakulte);
        personel.setBolum(bolum);

        personelRepository.save(personel);
    } 
    
    

}