package com.digitechfp.demo_spring.controller;

import com.digitechfp.demo_spring.entity.Estudiante;
import com.digitechfp.demo_spring.entity.PerfilAcademico;
import com.digitechfp.demo_spring.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // Listar todos los estudiantes
    @GetMapping
    public List<Estudiante> obtenerTodos() {
        return estudianteService.listarTodos();
    }

    // Buscar estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(
            @PathVariable Long id
    ) {
        return estudianteService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(
                                HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap(
                                "mensaje", "El estudiante no existe")));
    }

    // CREAR UN NUEVO ESTUDIANTE
    @PostMapping
    public ResponseEntity<?> crearEstudiante(@RequestBody Estudiante estudiante) {
        // Validamos si el email ya está registrado
        if (estudianteService.existsByCorreo(estudiante.getCorreo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap(
                            "mensaje", "El correo ya está registrado por otro estudiante"
                    ));
        }
        // Si el correo no está registrado, GUARDAMOS EL ESTUDIANTE
        Estudiante nuevoEstudiante =
                estudianteService.guardarEstudiante(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
    }

    // ACTUALIZAR
    // COMPLETO
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCompleto(
            @PathVariable Long id,
            @RequestBody Estudiante estudianteDatos
    ) {
        return estudianteService.buscarPorId(id)
                .<ResponseEntity<?>>map(estudianteExistente -> {
                    estudianteExistente.setNombre(estudianteDatos.getNombre());
                    estudianteExistente.setEdad(estudianteDatos.getEdad());
                    estudianteExistente.setCorreo(estudianteDatos.getCorreo());

                    // Actualizamos los datos de relación 1a1
                    if (estudianteDatos.getPerfilAcademico() != null) {
                        if (estudianteExistente.getPerfilAcademico() != null) {
                            estudianteExistente.getPerfilAcademico().setNivelIngles(
                                    estudianteDatos.getPerfilAcademico().getNivelIngles());
                            estudianteExistente.getPerfilAcademico().setBiografia(
                                    estudianteDatos.getPerfilAcademico().getBiografia());
                        }
                    } else {
                        estudianteExistente.setPerfilAcademico(null);
                    }

                    Estudiante estudianteActualizado =
                            estudianteService.guardarEstudiante(estudianteExistente);
                    return ResponseEntity.ok(estudianteActualizado);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("mensaje", "El estudiante no existe")));
    }

    // PARCIAL
    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos
    ) {
        return estudianteService.buscarPorId(id)
                .<ResponseEntity<?>>map(estudianteExistente -> {
                    campos.forEach((campo, valor) -> {
                        switch (campo) {
                            case "nombre":
                                estudianteExistente.setNombre((String) valor);
                                break;
                            case "correo":
                                estudianteExistente.setCorreo((String) valor);
                                break;
                            case "edad":
                                estudianteExistente.setEdad(((Number) valor).intValue());
                                break;
                            case "perfilAcademico":
                                if (valor instanceof Map){
                                    @SuppressWarnings("unchecked")
                                            Map<String, Object> datosPerfil =
                                            (Map<String, Object>) valor;

                                    PerfilAcademico perfilAcademico =
                                            estudianteExistente.getPerfilAcademico();
                                    if (perfilAcademico == null){
                                        perfilAcademico = new PerfilAcademico();
                                        estudianteExistente.setPerfilAcademico(perfilAcademico);
                                    }
                                    if(datosPerfil.containsKey("biografia")){
                                        perfilAcademico.setBiografia((String)datosPerfil.get("biografia"));
                                    }
                                    if(datosPerfil.containsKey("nivelIngles")){
                                        perfilAcademico.setNivelIngles((String)datosPerfil.get("nivelIngles"));
                                    }

                                } else if (valor == null) {
                                    estudianteExistente.setPerfilAcademico(null);
                                }
                                break;

                        }
                    });
                    Estudiante actualizado =
                            estudianteService.guardarEstudiante(estudianteExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("mensaje", "El estudiante no existe")));
    }

    // BORRAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEstudiante(@PathVariable Long id) {
        if (estudianteService.buscarPorId(id).isPresent()) {
            estudianteService.eliminarEstudiante(id);
            return ResponseEntity.ok(Collections.singletonMap(
                    "mensaje", "Usuario borrado"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(
                        "mensaje", "El estudiante no existe."));
    }


}
