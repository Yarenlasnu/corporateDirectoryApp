package com.kurumsalrehber.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BolumDTO {
    private Long id;
    private String ad;
    private Long fakulteId; // Fakülte ilişkisi DTO üzerinden id ile yapılacak
}
