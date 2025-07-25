package com.forohub.forohub.domain.curso.repositories;

import com.forohub.forohub.domain.curso.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
