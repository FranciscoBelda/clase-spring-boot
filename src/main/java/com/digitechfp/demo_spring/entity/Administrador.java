package com.digitechfp.demo_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrador")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo",nullable = false, length = 100)
    private String nombreCompleto;

    @Column(name = "departamento", nullable = false)
    private String departamento;

    @Column(name = "anio_inicial")
    private int anioInicial;

    public Administrador(String nombreCompleto, String departamento, int anioInicial) {
        this.nombreCompleto = nombreCompleto;
        this.departamento = departamento;
        this.anioInicial = anioInicial;
    }
}
