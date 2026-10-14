package com.digitechfp.demo_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estudiante")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "edad")
    private int edad;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    private PerfilAcademico perfilAcademico;

    public Estudiante(String nombre, String correo, int edad, PerfilAcademico perfilAcademico) {
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.perfilAcademico = perfilAcademico;
    }
}
