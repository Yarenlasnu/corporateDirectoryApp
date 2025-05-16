package com.kurumsalrehber.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fakulte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;

    @OneToMany(mappedBy = "fakulte", cascade = CascadeType.ALL)
    private List<Bolum> bolumler;
}
