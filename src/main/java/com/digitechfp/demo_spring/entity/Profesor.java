package com.digitechfp.demo_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profesor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombre;

    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    @Column(name = "experiencia_anios")
    private int experienciaAnios;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "oficina_id", referencedColumnName = "id")
    private Oficina oficina;

    public Profesor(String nombre, String especialidad, int experienciaAnios, Oficina oficina) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.experienciaAnios = experienciaAnios;
        this.oficina = oficina;
    }
}
