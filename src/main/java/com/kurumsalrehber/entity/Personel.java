package com.kurumsalrehber.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String soyad;
    private String telefon;

    @ManyToOne
    @JoinColumn(name = "bolum_id")
    private Bolum bolum;
    
    @ManyToOne
    @JoinColumn(name = "fakulte_id")
    private Fakulte fakulte;
}
