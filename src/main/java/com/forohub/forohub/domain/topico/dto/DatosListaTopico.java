package com.forohub.forohub.domain.topico.dto;

import com.forohub.forohub.domain.topico.models.EstadoTopico;
import com.forohub.forohub.domain.topico.models.Topico;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        EstadoTopico estado,
        LocalDateTime fechaCreacion,
        Long idCurso,
        Long idAutor,
        String nombreCurso,
        String nombreAutor
) {
    public DatosListaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstado(),
                topico.getFechaCreacion(),
                topico.getCurso().getId(),
                topico.getAutor().getId(),
                topico.getCurso().getNombre(),
                topico.getAutor().getEmail()
        );
    }
}
