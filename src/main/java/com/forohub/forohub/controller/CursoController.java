package com.forohub.forohub.controller;


import com.forohub.forohub.domain.curso.dto.DatosCurso;
import com.forohub.forohub.domain.curso.dto.DatosDetalleCurso;
import com.forohub.forohub.domain.curso.services.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cursos")
@SecurityRequirement(name="bearer-key")
public class CursoController {
    private final CursoService service;

    @PostMapping
    public ResponseEntity<DatosDetalleCurso> register(
            @RequestBody @Valid DatosCurso datos
    ) {
        var detalleCurso = service.registrar(datos);
        return ResponseEntity.ok(detalleCurso);
    }
}
