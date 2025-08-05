package com.forohub.forohub.domain.curso.dto;
import com.forohub.forohub.domain.curso.models.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCurso(
        @NotBlank String nombre,
        @NotBlank String descripcion,
        @NotNull Estado estado
) {
}
