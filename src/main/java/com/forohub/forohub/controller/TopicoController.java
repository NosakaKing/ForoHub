package com.forohub.forohub.controller;

import com.forohub.forohub.domain.topico.dto.DatosDetalleTopico;
import com.forohub.forohub.domain.topico.dto.DatosEditarTopico;
import com.forohub.forohub.domain.topico.dto.DatosListaTopico;
import com.forohub.forohub.domain.topico.dto.DatosTopico;
import com.forohub.forohub.domain.topico.services.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topicos")
@SecurityRequirement(name="bearer-key")
public class TopicoController {
    private final TopicoService service;

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listar( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.obtenerTopicos(page, size));
    }

    @GetMapping("/byId")
    public ResponseEntity<DatosListaTopico> obtener(@RequestParam Long id) {
        return ResponseEntity.ok(service.obtenerTopicoPorId(id));
    }

    @PostMapping
    public ResponseEntity<DatosDetalleTopico> register(@RequestBody @Valid DatosTopico datos) {
        var detalleTopico = service.registrar(datos);
        return ResponseEntity.ok().body(detalleTopico);
    }

    @PutMapping
    public ResponseEntity<DatosDetalleTopico> update(@RequestBody @Valid DatosEditarTopico datos) {
        var detalleTopico = service.editar(datos);
        return ResponseEntity.ok().body(detalleTopico);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> eliminar(@RequestParam Long id) {
        System.out.println(">>> ENTRANDO AL CONTROLADOR DELETE CON ID = " + id);
        service.eliminar(id);
        return ResponseEntity.ok("Topico eliminado");
    }

}
