package com.KurumsalRehber.controller;

import com.kurumsalrehber.controller.AdminController;
import com.kurumsalrehber.entity.Admin;
import com.kurumsalrehber.service.IBolumService;
import com.kurumsalrehber.service.IFakulteService;
import com.kurumsalrehber.service.IPersonelService;
import com.kurumsalrehber.service.impl.AdminService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    private AdminController controller;
    private IFakulteService fakulteService;
    private IBolumService bolumService;
    private IPersonelService personelService;
    private AdminService adminService;
    private HttpSession session;

    @BeforeEach
    void setup() {
        fakulteService = mock(IFakulteService.class);
        bolumService = mock(IBolumService.class);
        personelService = mock(IPersonelService.class);
        adminService = mock(AdminService.class);
        session = mock(HttpSession.class);

        controller = new AdminController();
        controller.setAdminService(adminService);
        controller.setFakulteService(fakulteService);
        controller.setBolumService(bolumService);
        controller.setPersonelService(personelService);

    }

    @Test
    void testLoginSayfasi() {
        assertEquals("login", controller.loginSayfasi());
    }

    @Test
    void testLoginKontrol_success() {
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("1234"));

        when(adminService.getByUsername("admin")).thenReturn(Optional.of(admin));
        String result = controller.loginKontrol("admin", "1234", session);
        assertEquals("redirect:/admin/panel", result);
    }

    @Test
    void testLoginKontrol_fail() {
        when(adminService.getByUsername("admin")).thenReturn(Optional.empty());
        String result = controller.loginKontrol("admin", "wrong", session);
        assertEquals("redirect:/admin/login?error=true", result);
    }

    @Test
    void testShowChangePasswordPage() {
        assertEquals("changePassword", controller.showChangePasswordPage());
    }

    @Test
    void testChangePassword_success() {
        Admin admin = new Admin();
        admin.setPassword(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("OldPass1!"));
        when(adminService.getByUsername("admin")).thenReturn(Optional.of(admin));

        Model model = new ConcurrentModel();
        String result = controller.changePassword("OldPass1!", "NewPass1!", "NewPass1!", session, model);
        assertEquals("changePassword", result);
        assertEquals("Şifreniz başarıyla güncellendi.", model.getAttribute("success"));
    }

    @Test
    void testChangePassword_wrongOldPassword() {
        Admin admin = new Admin();
        admin.setPassword(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("Correct1!"));
        when(adminService.getByUsername("admin")).thenReturn(Optional.of(admin));

        Model model = new ConcurrentModel();
        String result = controller.changePassword("Wrong!", "New1!", "New1!", session, model);
        assertEquals("changePassword", result);
        assertEquals("Mevcut şifre hatalı.", model.getAttribute("error"));
    }

    @Test
    void testGetAdminPanel_noSession() {
        when(session.getAttribute("admin")).thenReturn(null);
        String result = controller.getAdminPanel(null, null, new ConcurrentModel(), session);
        assertEquals("redirect:/admin/login", result);
    }

    @Test
    void testFakulteEkle() {
        String result = controller.fakulteEkle("Mühendislik");
        assertEquals("redirect:/admin/panel", result);
        verify(fakulteService).fakulteEkle("Mühendislik");
    }

    @Test
    void testBolumEkle() {
        String result = controller.bolumEkle(1L, "Bilgisayar");
        assertEquals("redirect:/admin/panel?fakulteId=1", result);
        verify(bolumService).bolumEkle(1L, "Bilgisayar");
    }

    @Test
    void testPersonelEkle() {
        String result = controller.personelEkle(1L, 2L, "Ali", "Yılmaz", "0380 000 00 00");
        assertEquals("redirect:/admin/panel?fakulteId=1&seciliBolumId=2", result);
        verify(personelService).personelEkle(1L, 2L, "Ali", "Yılmaz", "0380 000 00 00");
    }

    @Test
    void testFakulteSil() {
        String result = controller.fakulteSil(5L);
        assertEquals("redirect:/admin/panel", result);
        verify(fakulteService).fakulteSil(5L);
    }

    @Test
    void testBolumSil() {
        String result = controller.bolumSil(4L, 3L);
        assertEquals("redirect:/admin/panel?fakulteId=3", result);
        verify(bolumService).bolumSil(4L);
    }

    @Test
    void testPersonelSil() {
        String result = controller.personelSil(10L, 1L, 2L);
        assertEquals("redirect:/admin/panel?fakulteId=1&seciliBolumId=2", result);
        verify(personelService).personelSil(10L);
    }

    @Test
    void testLogout() {
        String result = controller.logout(session);
        verify(session).invalidate();
        assertEquals("redirect:/admin/login", result);
    }
}
