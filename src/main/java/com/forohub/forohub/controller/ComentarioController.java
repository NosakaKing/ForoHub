package com.forohub.forohub.controller;

import com.forohub.forohub.domain.ResponseApi;
import com.forohub.forohub.domain.comentario.dto.DatosComentario;
import com.forohub.forohub.domain.comentario.dto.DatosListaComentario;
import com.forohub.forohub.domain.comentario.services.ComentarioService;
import com.forohub.forohub.domain.usuario.models.Usuario;
import com.forohub.forohub.infra.util.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comentarios")
public class ComentarioController {
    private final ComentarioService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/byId")
    public ResponseEntity<List<DatosListaComentario>> listar(@RequestParam Long id) {
        return ResponseEntity.ok(service.obtenerComentarios(id));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<ResponseApi> registrar(@RequestBody @Valid DatosComentario datos, @AuthenticationPrincipal Usuario usuario){
        service.registrar(datos, usuario.getId());
        return Response.success("Registrado comentario");
    }
}
