package com.forohub.forohub.controller;

import com.forohub.forohub.domain.usuario.dto.DatosAutenticacion;
import com.forohub.forohub.domain.usuario.dto.DatosUsuario;
import com.forohub.forohub.domain.usuario.service.AuthService;
import com.forohub.forohub.infra.security.DatosTokenJWT;
import com.forohub.forohub.infra.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacionController {
    private final AuthService service;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<DatosTokenJWT> login(
            @RequestBody @Valid DatosAutenticacion datos,
            HttpServletResponse response
    ) {
        var tokenData = service.login(datos, response);
        return ResponseEntity.ok(tokenData);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        response.addCookie(CookieUtil.clearAccessTokenCookie());
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/whoami")
    public ResponseEntity<DatosUsuario> whoAmI() {
        return ResponseEntity.ok(service.obtenerUsuarioLogeado());
    }
}
