package com.kurumsalrehber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonelDTO {
    private Long id;
    private String ad;
    private String soyad;
    private String telefon;
    private Long bolumId;
    private String bolumAd;
    private String fakulteAd;
    
    public PersonelDTO(String ad, String soyad, String fakulteAd, String bolumAd, String telefon) {
        this.ad = ad;
        this.soyad = soyad;
        this.fakulteAd = fakulteAd;
        this.bolumAd = bolumAd;
        this.telefon = telefon;
    }


}
