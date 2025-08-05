package com.forohub.forohub.domain.comentario.dto;

import com.forohub.forohub.domain.comentario.models.Comentario;

import java.time.LocalDateTime;

public record DatosListaComentario(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        String nombreAutor,
        Long idAutor
        ) {
    public DatosListaComentario(Comentario comentario) {
        this(
                comentario.getId(),
                comentario.getMensaje(),
                comentario.getFechaCreacion(),
                comentario.getAutor().getApellido() + " "  + comentario.getAutor().getNombre(),
                comentario.getAutor().getId()
        );
    }
}
