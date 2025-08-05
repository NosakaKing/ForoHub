package com.forohub.forohub.infra.security;

public record DatosTokenJWT(
        String username, String message, Boolean status
) {
}
