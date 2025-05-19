package com.KurumsalRehber.controller;

import com.kurumsalrehber.controller.UserController;
import com.kurumsalrehber.dto.PersonelDTO;
import com.kurumsalrehber.service.IPersonelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserController userController;
    private IPersonelService personelService;
    private Model model;

    @BeforeEach
    public void setUp() {
        personelService = mock(IPersonelService.class);
        model = mock(Model.class);

        userController = new UserController();
        injectField(userController, "personelService", personelService);
    }

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
    public void testKullaniciPaneli() {
        String viewName = userController.kullaniciPaneli(model);
        assertEquals("kullaniciPanel", viewName);
    }

    @Test
    public void testKullaniciAra_withValidQuery() {
        String arama = "Ahmet";
        List<PersonelDTO> dummyList = List.of(new PersonelDTO());

        when(personelService.search(arama)).thenReturn(dummyList);

        String viewName = userController.kullaniciAra(arama, model);

        verify(model).addAttribute(eq("sonucListesi"), eq(dummyList));
        assertEquals("kullaniciPanel", viewName);
    }

    @Test
    public void testKullaniciAra_withEmptyQuery() {
        String viewName = userController.kullaniciAra("   ", model);

        verify(model).addAttribute(eq("sonucListesi"), isNull());
        assertEquals("kullaniciPanel", viewName);
    }
}
