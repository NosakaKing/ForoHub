package com.forohub.forohub.domain.topico.repositories;
import com.forohub.forohub.domain.topico.models.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRespository extends JpaRepository<Topico, Long> {
    Page<Topico> findByCursoId(Long cursoId, Pageable pageable);
}
