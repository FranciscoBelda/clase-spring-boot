package com.digitechfp.demo_spring.config;

import com.digitechfp.demo_spring.entity.Estudiante;
import com.digitechfp.demo_spring.entity.Profesor;
import com.digitechfp.demo_spring.repository.EstudianteRepository;
import com.digitechfp.demo_spring.repository.ProfesorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class InicializadorDatos {
    @Bean
    CommandLineRunner initData(EstudianteRepository estudianteRepository, ProfesorRepository profesorRepository){
        return args -> {
            // INSERTAR ESTUDIANTES
            Estudiante estudiante1 = new Estudiante("Ana García",
                    "ana.garcia@digitechfp.com",19);
            Estudiante estudiante2 = new Estudiante("Pedro Martínez",
                    "pedro.martinez@digitechfp.com",18);

            estudianteRepository.save(estudiante1);
            estudianteRepository.save(estudiante2);

            System.out.println(">>>> Estudiantes guardados " +
                    "correctamente en la Base de Datos");

            // CONSULTAR DATOS DE LA BD
            System.out.println(">>>> Listado completo de estudiantes:");
            for (Estudiante e: estudianteRepository.findAll()){
                System.out.println("- ID: "+e.getId()+
                        " - "+e.getNombre() + " ("+e.getCorreo()+")");
            }

            // CONSULTAR UN ESTUDIANTE
            System.out.println(">>>> BUSCAMOS POR ID");
            Optional<Estudiante> e = estudianteRepository.findById(1L);
            e.ifPresent(estudiante ->
                    System.out.println("- ID: "+estudiante.getId()+
                            " - "+estudiante.getNombre() +
                            " ("+estudiante.getCorreo()+")"));

            // MODIFICAR DATOS
            System.out.println("\n>>>> ACTUALIZAMOS EL ID 2  ");
            Estudiante estudianteEncontrado =
                    estudianteRepository.findById(2L).orElseThrow();

            estudianteEncontrado.setNombre("David María Jardinero");
            estudianteEncontrado.setCorreo("david.maria.jardinero@digitechfp.com");
            estudianteEncontrado.setEdad(20);

            estudianteRepository.save(estudianteEncontrado);

            System.out.println(">>>> El resultado es:");
            System.out.println("Actualizado: "+
                    estudianteRepository.findById(2L).orElse(null));

            // CONTAR LOS REGISTROS DE ESTUDIANTE
            System.out.println(">>>> Contamos los estudiantes");
            System.out.println("El total de estudiantes es: "+
                    estudianteRepository.count());

            // BORRAR UN ESTUDIANTE
            System.out.println(">>>> Borramos el ID 2");
            estudianteRepository.deleteById(2L);

            System.out.println(">>>> LISTADO FINAL DE ESTUDIANTES");
            for (Estudiante est: estudianteRepository.findAll()){
                System.out.println("- ID: "+est.getId()+ " - "+
                        est.getNombre()+" ("+est.getCorreo()+")");
            }


            System.out.println("\n>>>> PROFESORES");
            Profesor profesor = new Profesor("Pablo Laoz", "Proyecto Intermodular",33);
            Profesor profesor1 = new Profesor("Laura Segurola", "PMDM",36);
            Profesor profesor2 = new Profesor("Raúl Martínez", "Diseño de interfaces",31);


            //profesorRepository.saveAll(List.of(profesor,profesor1,profesor2));
            profesorRepository.save(profesor);
            profesorRepository.save(profesor1);
            profesorRepository.save(profesor2);


            // ACTUALIZO EL 2
            System.out.println(">>>> ACTUALIZAMOS EL 2");
            Profesor profesorEncontrado =
                    profesorRepository.findById(2L).orElseThrow();
            profesorEncontrado.setNombre("David Juan");
            profesorEncontrado.setEspecialidad("Programación");
            profesorEncontrado.setExperienciaAnios(22);
            profesorRepository.save(profesorEncontrado);

            System.out.println(">>>> ACTUALIZACiÓN DE PROFESORES");
            for (Profesor p : profesorRepository.findAll()){
                System.out.println("-ID: "+p.getId()+" "+p.getNombre()+
                        " "+p.getEspecialidad()+" - "+p.getExperienciaAnios()+" años trabajados");
            }

            System.out.println(">>>> BORRAMOS EL 2");
            profesorRepository.deleteById(2L);

            System.out.println(">>>> LISTADO FINAL DE PROFESORES");
            for (Profesor p : profesorRepository.findAll()){
                System.out.println("-ID: "+p.getId()+" "+p.getNombre()+
                        " "+p.getEspecialidad()+" - "+p.getExperienciaAnios()+" años trabajados");
            }







        };
    }
}
