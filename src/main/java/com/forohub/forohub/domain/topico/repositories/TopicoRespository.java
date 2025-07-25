package com.forohub.forohub.domain.topico.repositories;

import com.forohub.forohub.domain.topico.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRespository extends JpaRepository<Topico, Long> {
}
