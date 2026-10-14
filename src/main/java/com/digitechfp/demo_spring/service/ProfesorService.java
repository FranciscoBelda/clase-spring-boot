package com.digitechfp.demo_spring.service;

import com.digitechfp.demo_spring.entity.Profesor;
import com.digitechfp.demo_spring.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<Profesor> listarTodos(){
        return profesorRepository.findAll();
    }

    public Optional<Profesor> buscarPorId(Long id){
        return profesorRepository.findById(id);
    }

    public Profesor guardarProfesor(Profesor profesor){
        return profesorRepository.save(profesor);
    }

    public void eliminarProfesor(Long id){
        profesorRepository.deleteById(id);
    }
}
