package com.forohub.forohub.domain.curso.dto;

import com.forohub.forohub.domain.curso.models.Curso;

public record DatosListaCurso(
        Long id,
        String nombre
) {
    public DatosListaCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre()
        );
    }
}
