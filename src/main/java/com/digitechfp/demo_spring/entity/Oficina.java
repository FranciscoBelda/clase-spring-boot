package com.digitechfp.demo_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "oficina")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oficina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_oficina", length = 20)
    private String codigoOficina;

    @Column(name = "edificio", length = 10)
    private String edificio;

    public Oficina(String codigoOficina, String edificio) {
        this.codigoOficina = codigoOficina;
        this.edificio = edificio;
    }
}
