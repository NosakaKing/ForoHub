package com.forohub.forohub.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosNuevoUsuario(
        @NotBlank @Email  String email,
        @NotBlank @Size(min = 2, max = 50)
        String nombre,
        @NotBlank @Size(min = 2, max = 50)
        String apellido,
        @NotBlank @Size(min = 5, max = 20)
        String nombreUsuario,
        @NotBlank @Size(min = 8, max = 20)
        String contrasenia
        ) {
}
