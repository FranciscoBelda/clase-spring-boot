package com.digitechfp.demo_spring.service;

import com.digitechfp.demo_spring.entity.Estudiante;
import com.digitechfp.demo_spring.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    // Obtener todos los estudiantes
    public List<Estudiante> listarTodos(){
        return estudianteRepository.findAll();
    }

    // Buscar estudiante por ID
    public Optional<Estudiante> buscarPorId(Long id){
        return estudianteRepository.findById(id);
    }

    // Guardar o actualizar
    public Estudiante guardarEstudiante(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    // Borrar un estudiante
    public void eliminarEstudiante(Long id){
        estudianteRepository.deleteById(id);
    }

    // Buscar si existe un correo
    public boolean existsByCorreo(String correo){
        return estudianteRepository.existsByCorreo(correo);
    }

    // Buscar un estudiante por su correo
    public Estudiante buscarPorCorreo(String correo){
        return estudianteRepository.findByCorreo(correo);
    }


}
