package com.KurumsalRehber.controller;


import com.kurumsalrehber.controller.BolumController;
import com.kurumsalrehber.dto.BolumDTO;
import com.kurumsalrehber.service.IBolumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BolumControllerTest {

    private IBolumService bolumService;
    private BolumController bolumController;

    @BeforeEach
    public void setUp() {
        bolumService = mock(IBolumService.class);
        bolumController = new BolumController(bolumService);
    }

    @Test
    public void testBolumEkle() {
        BolumDTO girisDto = new BolumDTO();
        girisDto.setAd("Bilgisayar Mühendisliği");

        BolumDTO beklenenDto = new BolumDTO();
        beklenenDto.setAd("Bilgisayar Mühendisliği");

        when(bolumService.save(girisDto)).thenReturn(beklenenDto);

        BolumDTO sonuc = bolumController.bolumEkle(girisDto);

        assertEquals("Bilgisayar Mühendisliği", sonuc.getAd());
        verify(bolumService).save(girisDto);
    }

    @Test
    public void testBolumleriListele() {
        List<BolumDTO> beklenenListe = List.of(new BolumDTO(), new BolumDTO());

        when(bolumService.getAll()).thenReturn(beklenenListe);

        List<BolumDTO> sonuc = bolumController.bolumleriListele();

        assertEquals(2, sonuc.size());
        verify(bolumService).getAll();
    }

    @Test
    public void testFakulteyeGoreBolumler() {
        Long fakulteId = 1L;
        List<BolumDTO> beklenenListe = List.of(new BolumDTO());

        when(bolumService.getByFakulteId(fakulteId)).thenReturn(beklenenListe);

        List<BolumDTO> sonuc = bolumController.fakulteyeGoreBolumler(fakulteId);

        assertEquals(1, sonuc.size());
        verify(bolumService).getByFakulteId(fakulteId);
    }

    @Test
    public void testBolumSil() {
        Long bolumId = 5L;

        bolumController.bolumSil(bolumId);

        verify(bolumService).deleteById(bolumId);
    }
}
