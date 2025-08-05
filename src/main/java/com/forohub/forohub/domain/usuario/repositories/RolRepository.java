package com.forohub.forohub.domain.usuario.repositories;

import com.forohub.forohub.domain.usuario.models.Rol;
import com.forohub.forohub.domain.usuario.models.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRol(UserRoles rol);
    boolean existsByRol(UserRoles rol);
}
