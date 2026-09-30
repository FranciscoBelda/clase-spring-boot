package com.digitechfp.demo_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CursoInfoDto {
    private String nombreCurso;
    private String centroEducativo;
    private String profesor;
}
