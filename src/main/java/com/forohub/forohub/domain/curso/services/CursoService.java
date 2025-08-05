package com.forohub.forohub.domain.curso.services;

import com.forohub.forohub.domain.curso.dto.DatosCurso;
import com.forohub.forohub.domain.curso.dto.DatosDetalleCurso;
import com.forohub.forohub.domain.curso.dto.DatosListaCurso;
import com.forohub.forohub.domain.curso.models.Curso;
import com.forohub.forohub.domain.curso.repositories.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CursoService {
    private final CursoRepository repository;

    public DatosDetalleCurso registrar(DatosCurso datos) {
        var curso = new Curso(null, datos.nombre(), datos.descripcion(), datos.estado());
        repository.save(curso);
        return new DatosDetalleCurso(curso);
    }

    public List<DatosListaCurso> obtenerCursos() {
        var cursos = repository.findAll();
        return cursos.stream().map(DatosListaCurso::new).collect(Collectors.toList());
    }
}
