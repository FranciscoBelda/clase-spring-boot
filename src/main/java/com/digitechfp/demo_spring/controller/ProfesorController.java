package com.digitechfp.demo_spring.controller;

import com.digitechfp.demo_spring.entity.Profesor;
import com.digitechfp.demo_spring.service.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public List<Profesor> listarTodos(){
        return profesorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(
            @PathVariable Long id
    ){
        return profesorService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("mensaje","El profesor no existe")));
    }

    @PostMapping
    public ResponseEntity<Profesor> crearProfesor(
            @RequestBody Profesor profesor
    ){
        Profesor nuevoProfesor = profesorService.guardarProfesor(profesor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevoProfesor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCompleto(
            @PathVariable Long id,
            @RequestBody Profesor profesorDatos
    ){
        return profesorService.buscarPorId(id)
                .<ResponseEntity<?>>map(profesorExistente -> {
                    profesorExistente.setNombre(profesorDatos.getNombre());
                    profesorExistente.setEspecialidad(profesorDatos.getEspecialidad());
                    profesorExistente.setExperienciaAnios(profesorDatos.getExperienciaAnios());



                    Profesor profesorActualizado = profesorService.guardarProfesor(profesorExistente);
                    return ResponseEntity.ok(profesorActualizado);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("mensaje","El profesor no existe")));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos
            ){
        return profesorService.buscarPorId(id)
                .<ResponseEntity<?>>map(profesorExistente -> {
                    campos.forEach((campo, valor) -> {
                        switch (campo){
                            case "nombre":
                                profesorExistente.setNombre((String) valor);
                                break;
                            case "especialidad":
                                profesorExistente.setEspecialidad((String) valor);
                                break;
                            case "experienciaAnios":
                                if (valor instanceof Number) {
                                    profesorExistente.setExperienciaAnios((Integer) valor);
                                }
                                break;
                        }
                    });

                    Profesor actualizado = profesorService.guardarProfesor(profesorExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("mensaje","El profesor no existe")));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarProfesor(
            @PathVariable Long id
    ){
        if (profesorService.buscarPorId(id).isPresent()){
            profesorService.eliminarProfesor(id);
            return ResponseEntity.ok(Collections.singletonMap("mensaje","Profesor eliminado"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("mensaje","El profesor no existe"));

    }
}

