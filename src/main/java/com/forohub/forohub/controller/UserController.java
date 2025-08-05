package com.forohub.forohub.controller;

import com.forohub.forohub.domain.ResponseApi;
import com.forohub.forohub.domain.usuario.dto.DatosNuevoUsuario;
import com.forohub.forohub.domain.usuario.service.UsuarioService;
import com.forohub.forohub.infra.util.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UserController {
    private final UsuarioService service;

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<ResponseApi> registrar(@RequestBody @Valid DatosNuevoUsuario datos) {
        service.registrarUsuario(datos);
        return Response.success("Nuevo Usuario creado satisfactoriamente");
    }
}
