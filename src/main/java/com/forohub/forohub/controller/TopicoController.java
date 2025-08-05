package com.forohub.forohub.controller;

import com.forohub.forohub.domain.ResponseApi;
import com.forohub.forohub.domain.topico.dto.*;
import com.forohub.forohub.domain.topico.services.TopicoService;
import com.forohub.forohub.domain.usuario.models.Usuario;
import com.forohub.forohub.infra.util.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listar( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.obtenerTopicos(page, size));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/byId")
    public ResponseEntity<DatosUnTopico> obtener(@RequestParam Long id) {
        return ResponseEntity.ok(service.obtenerTopicoPorId(id));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<DatosDetalleTopico> registrar(@RequestBody @Valid DatosTopico datos, @AuthenticationPrincipal Usuario usuario
    ) {
        var detalleTopico = service.registrar(datos, usuario.getId());
        return ResponseEntity.ok().body(detalleTopico);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public ResponseEntity<DatosDetalleTopico> update(@RequestBody @Valid DatosEditarTopico datos) {
        var detalleTopico = service.editar(datos);
        return ResponseEntity.ok().body(detalleTopico);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteById")
    public ResponseEntity<ResponseApi> eliminar(@RequestParam Long id) {
        service.eliminar(id);
        return Response.success("Topico eliminado");
    }
}
