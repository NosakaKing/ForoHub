package com.forohub.forohub.domain.comentario.repositories;

import com.forohub.forohub.domain.comentario.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByTopicoId(Long topicoId);
    @Query("SELECT COUNT(c) FROM Comentario c WHERE c.topico.id = :topicoId")
    Long contarRespuestasPorTopico(Long topicoId);

}
