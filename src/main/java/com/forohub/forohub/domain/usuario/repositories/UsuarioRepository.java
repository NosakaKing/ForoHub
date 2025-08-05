package com.forohub.forohub.domain.usuario.repositories;

import com.forohub.forohub.domain.usuario.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String username);
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario OR u.email = :nombreUsuario")
    Optional<Usuario> findByNombreUsuarioOrEmail(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByEmail(String email);
}
