package com.forohub.forohub.domain.topico.dto;

import com.forohub.forohub.domain.topico.models.EstadoTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosEditarTopico(
        @NotNull Long id,
        String titulo,
        String mensaje
) {

}
