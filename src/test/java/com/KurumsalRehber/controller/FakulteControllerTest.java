package com.KurumsalRehber.controller;


import com.kurumsalrehber.controller.FakulteController;
import com.kurumsalrehber.dto.FakulteDTO;
import com.kurumsalrehber.service.IFakulteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FakulteControllerTest {

    private IFakulteService fakulteService;
    private FakulteController fakulteController;

    @BeforeEach
    public void setUp() {
        fakulteService = mock(IFakulteService.class);
        fakulteController = new FakulteController();
        injectField(fakulteController, "fakulteService", fakulteService);
    }

    // Reflection yardımıyla autowired alanı set etmek için
    private void injectField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testFakulteEkle() {
        FakulteDTO input = new FakulteDTO();
        input.setAd("Mühendislik Fakültesi");

        FakulteDTO expected = new FakulteDTO();
        expected.setAd("Mühendislik Fakültesi");

        when(fakulteService.save(input)).thenReturn(expected);

        FakulteDTO result = fakulteController.fakulteEkle(input);

        assertNotNull(result);
        assertEquals("Mühendislik Fakültesi", result.getAd());
        verify(fakulteService).save(input);
    }

    @Test
    public void testFakulteListele() {
        List<FakulteDTO> expectedList = List.of(new FakulteDTO(), new FakulteDTO());

        when(fakulteService.getAll()).thenReturn(expectedList);

        List<FakulteDTO> result = fakulteController.fakulteListele();

        assertEquals(2, result.size());
        verify(fakulteService).getAll();
    }

    @Test
    public void testFakulteSil() {
        Long fakulteId = 3L;

        fakulteController.fakulteSil(fakulteId);

        verify(fakulteService).deleteById(fakulteId);
    }
}
