package com.forohub.forohub.domain.topico.dto;

import com.forohub.forohub.domain.topico.models.EstadoTopico;
import com.forohub.forohub.domain.topico.models.Topico;

import java.time.LocalDateTime;

public record DatosUnTopico(
        Long id,
        String titulo,
        String mensaje,
        EstadoTopico estado,
        LocalDateTime fechaCreacion,
        Long idAutor,
        String nombreCurso,
        String nombreAutor,
        Long totalComentarios,
        Long solucionId
) {
    public DatosUnTopico(Topico topico, Long totalComentarios) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstado(),
                topico.getFechaCreacion(),
                topico.getAutor().getId(),
                topico.getCurso().getNombre(),
                topico.getAutor().getApellido() + " "  + topico.getAutor().getNombre(),
                totalComentarios,
                topico.getSolucionId()
        );
    }
}
