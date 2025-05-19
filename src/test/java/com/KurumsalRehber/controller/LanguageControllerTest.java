package com.KurumsalRehber.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kurumsalrehber.controller.LanguageController;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LanguageControllerTest {

    private LanguageController languageController;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private LocaleResolver localeResolver;

    @BeforeEach
    public void setUp() {
        languageController = new LanguageController();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        localeResolver = mock(LocaleResolver.class);
    }

    @Test
    public void testChangeLanguage_WithReferer() {
        when(request.getHeader("Referer")).thenReturn("/kullanici/panel");

        try (MockedStatic<RequestContextUtils> mockedStatic = mockStatic(RequestContextUtils.class)) {
            mockedStatic.when(() -> RequestContextUtils.getLocaleResolver(request))
                        .thenReturn(localeResolver);

            String result = languageController.changeLanguage("en", request, response);

            verify(localeResolver).setLocale(request, response, Locale.forLanguageTag("en"));
            assertEquals("redirect:/kullanici/panel", result);
        }
    }

    @Test
    public void testChangeLanguage_NoReferer() {
        when(request.getHeader("Referer")).thenReturn(null);

        try (MockedStatic<RequestContextUtils> mockedStatic = mockStatic(RequestContextUtils.class)) {
            mockedStatic.when(() -> RequestContextUtils.getLocaleResolver(request))
                        .thenReturn(localeResolver);

            String result = languageController.changeLanguage("tr", request, response);

            verify(localeResolver).setLocale(request, response, Locale.forLanguageTag("tr"));
            assertEquals("redirect:/", result);
        }
    }

    @Test
    public void testChangeLanguage_NoLocaleResolver() {
        when(request.getHeader("Referer")).thenReturn("/");

        try (MockedStatic<RequestContextUtils> mockedStatic = mockStatic(RequestContextUtils.class)) {
            mockedStatic.when(() -> RequestContextUtils.getLocaleResolver(request))
                        .thenReturn(null);

            String result = languageController.changeLanguage("en", request, response);

            // LocaleResolver null olduğu için setLocale çağrılmaz
            assertEquals("redirect:/", result);
        }
    }
}

