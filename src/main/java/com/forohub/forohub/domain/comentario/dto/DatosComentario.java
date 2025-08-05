package com.forohub.forohub.domain.comentario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosComentario(
        @NotBlank String mensaje,
        @NotNull Long idTopico
) {
}
