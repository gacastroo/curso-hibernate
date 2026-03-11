package com.pizzeria.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pizzas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;
}