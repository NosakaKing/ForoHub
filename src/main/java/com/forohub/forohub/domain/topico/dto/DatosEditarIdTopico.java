package com.forohub.forohub.domain.topico.dto;

import com.forohub.forohub.domain.topico.models.Topico;

public record DatosEditarIdTopico(
        Long id,
        String titulo,
        String mensaje
) {
    public DatosEditarIdTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje()
        );
    }
}
