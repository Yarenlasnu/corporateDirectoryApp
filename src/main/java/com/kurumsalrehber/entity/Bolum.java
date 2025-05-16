package com.kurumsalrehber.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bolum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;

    @ManyToOne
    @JoinColumn(name = "fakulte_id")
    private Fakulte fakulte;

    @OneToMany(mappedBy = "bolum", cascade = CascadeType.ALL)
    private List<Personel> personeller;
}
