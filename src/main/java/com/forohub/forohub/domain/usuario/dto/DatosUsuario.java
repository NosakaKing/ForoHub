package com.forohub.forohub.domain.usuario.dto;
import com.forohub.forohub.domain.usuario.models.UserRoles;
import com.forohub.forohub.domain.usuario.models.Usuario;

public record DatosUsuario(
        Long id,
        String nombre,
        String apellido,
        String nombreUsuario,
        String correo,
        UserRoles rol
) {
    public DatosUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getNombreUsuario(),
                usuario.getEmail(),
                usuario.getRol().getRol()
        );
    }
}
