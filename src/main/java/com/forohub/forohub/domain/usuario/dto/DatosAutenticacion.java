package com.forohub.forohub.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(
        @NotBlank  String usuario,
        @NotBlank  String contrasenia
) {
}
