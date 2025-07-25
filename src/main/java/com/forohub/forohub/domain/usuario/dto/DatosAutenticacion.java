package com.forohub.forohub.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(
        @NotBlank @Email String email,
        @NotBlank  String contrasenia
) {
}
