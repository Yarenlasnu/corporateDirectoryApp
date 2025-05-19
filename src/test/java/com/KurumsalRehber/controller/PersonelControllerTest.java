package com.KurumsalRehber.controller;

import com.kurumsalrehber.controller.PersonelController;
import com.kurumsalrehber.dto.PersonelDTO;
import com.kurumsalrehber.service.IPersonelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PersonelControllerTest {

    private IPersonelService personelService;
    private PersonelController personelController;

    @BeforeEach
    public void setUp() {
        personelService = mock(IPersonelService.class);
        personelController = new PersonelController();
        injectField(personelController, "personelService", personelService);
    }

    // Reflection ile service injection
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
    public void testPersonelEkle() {
        PersonelDTO input = new PersonelDTO();
        input.setAd("Ahmet");
        input.setSoyad("Demir");
        input.setTelefon("0380 555 55 55");

        PersonelDTO expected = new PersonelDTO();
        expected.setAd("Ahmet");
        expected.setSoyad("Demir");
        expected.setTelefon("0380 555 55 55");

        when(personelService.save(input)).thenReturn(expected);

        PersonelDTO result = personelController.personelEkle(input);

        assertNotNull(result);
        assertEquals("Ahmet", result.getAd());
        verify(personelService).save(input);
    }

    @Test
    public void testPersonelListele() {
        List<PersonelDTO> expectedList = List.of(new PersonelDTO(), new PersonelDTO());
        when(personelService.getAll()).thenReturn(expectedList);

        List<PersonelDTO> result = personelController.personelListele();

        assertEquals(2, result.size());
        verify(personelService).getAll();
    }

    @Test
    public void testBolumeGorePersonelListele() {
        Long bolumId = 5L;
        List<PersonelDTO> expectedList = List.of(new PersonelDTO());
        when(personelService.getByBolumId(bolumId)).thenReturn(expectedList);

        List<PersonelDTO> result = personelController.bolumeGorePersonelListele(bolumId);

        assertEquals(1, result.size());
        verify(personelService).getByBolumId(bolumId);
    }

    @Test
    public void testPersonelSil() {
        Long personelId = 8L;

        personelController.personelSil(personelId);

        verify(personelService).deleteById(personelId);
    }
}
