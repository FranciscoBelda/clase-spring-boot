package com.digitechfp.demo_spring.controller;

import com.digitechfp.demo_spring.dto.CursoInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public String saludar(@RequestParam(value = "nombre",
            defaultValue = "Mundo") String nombre){
        return "¡Hola, "+ nombre +
                "! Bienvenido a la clase de Spring Boot de DAM.";
    }

    @GetMapping("/info")
    public CursoInfoDto obtenerInformacion(){
        return new CursoInfoDto("Desarrollo de Aplicaciones Multiplataforma (DAM)",
                "DigitechFP Valencia - Progresa",
                "Francisco Belda");
    }

    @GetMapping("/suma")
    public String sumar(
            @RequestParam(value = "a") int numeroA,
            @RequestParam(value = "b") int numeroB
    ){
        int resultado = numeroA + numeroB;
        return "El resultado de sumar " + numeroA + " y " + numeroB +
                " es: " + resultado;
    }
}
