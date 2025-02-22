package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Alumno;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    List<Alumno> alumnos = new ArrayList<>(Arrays.asList(
            new Alumno(1, "Pedro", "pedro@gmail.com", 19, "Modelos 1"),
            new Alumno(2, "Angela", "angela@hotmail.com", 20, "Ciencias 1")
    ));

    @GetMapping
    public List<Alumno> getAlumnos(){
        return alumnos;
    }

    @GetMapping("/{correo}")
    public Alumno getAlumnoById(@PathVariable String correo){
        for(Alumno a: alumnos){
            if(a.getEmail().equals(correo))
                return a;
        }
        return null;
    }

    @PostMapping
    public Alumno createAlumno(@RequestBody Alumno alumno){
        if(alumnos.size() < 4){
            alumnos.add(alumno);
            return alumno;
        }
        return null;
    }

    @PutMapping
    public Alumno updateAlumno(@RequestBody Alumno alumno){
        for(Alumno a: alumnos){
            if(a.getId() == alumno.getId()){
                a.setNombre(alumno.getNombre());
                a.setEmail(alumno.getEmail());
                a.setEdad(alumno.getEdad());
                a.setCurso(alumno.getCurso());
                return a;
            }
        }
        return null;
    }

    @PatchMapping
    public Alumno patchAlumno(@RequestBody Alumno alumno){
        for(Alumno a: alumnos){
            if(a.getId() == alumno.getId()){
                if(alumno.getNombre() != null){
                    a.setNombre(alumno.getNombre());
                }
                if(alumno.getEmail() != null){
                    a.setEmail(alumno.getEmail());
                }
                if(alumno.getEdad() != 0){
                    a.setEdad(alumno.getEdad());
                }
                if(alumno.getCurso() != null){
                    a.setCurso(alumno.getCurso());
                }
                return a;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Alumno deleteAlumno(@PathVariable int id){
        for(Alumno a: alumnos){
            if(a.getId() == id){
                alumnos.remove(a);
                return a;
            }
        }
        return null;
    }
}
