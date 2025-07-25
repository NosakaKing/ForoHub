package com.forohub.forohub.domain.curso.dto;

import com.forohub.forohub.domain.curso.models.Curso;
import com.forohub.forohub.domain.curso.models.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosDetalleCurso(
        Long id,
        String nombre,
        String descripcion,
        Estado estado
        ) {
        public DatosDetalleCurso(Curso curso) {
                this(
                        curso.getId(),
                        curso.getNombre(),
                        curso.getDescripcion(),
                        curso.getEstado()
                );
        }
}
