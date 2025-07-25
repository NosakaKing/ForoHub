package com.forohub.forohub.controller;

import com.forohub.forohub.domain.usuario.dto.DatosAutenticacion;
import com.forohub.forohub.domain.usuario.models.Usuario;
import com.forohub.forohub.infra.security.DatosTokenJWT;
import com.forohub.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacionController {
    private final TokenService tokenService;
    private final AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<DatosTokenJWT> login(
            @RequestBody @Valid DatosAutenticacion datos
    ) {
        var autenticationtoken = new UsernamePasswordAuthenticationToken(datos.email(), datos.contrasenia());
        var autenticacion = manager.authenticate(autenticationtoken);
        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT, datos.email()));
    }
}
