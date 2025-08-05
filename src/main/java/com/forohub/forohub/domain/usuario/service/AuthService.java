package com.forohub.forohub.domain.usuario.service;


import com.forohub.forohub.domain.ValidacionException;
import com.forohub.forohub.domain.usuario.dto.DatosAutenticacion;
import com.forohub.forohub.domain.usuario.dto.DatosUsuario;
import com.forohub.forohub.domain.usuario.models.Usuario;
import com.forohub.forohub.domain.usuario.repositories.UsuarioRepository;
import com.forohub.forohub.infra.security.DatosTokenJWT;
import com.forohub.forohub.infra.security.TokenService;
import com.forohub.forohub.infra.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenService tokenService;
    private final AuthenticationManager manager;
    private final UsuarioRepository repository;

    public DatosTokenJWT login(DatosAutenticacion datos,  HttpServletResponse response) {
        String username = datos.usuario();
        String password = datos.contrasenia();

        try {
            var autenticationtoken = new UsernamePasswordAuthenticationToken(username, password);
            var autenticacion = manager.authenticate(autenticationtoken);
            var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());
            Cookie cookie = CookieUtil.createAccessTokenCookie(tokenJWT, 7 * 24 * 60 * 60);
            response.addCookie(cookie);
            return new DatosTokenJWT(username, "Se ha iniciado sesión correctamente", true);
        } catch (AccountExpiredException e) {
            throw new BadCredentialsException("Cuenta expirada");
        } catch (CredentialsExpiredException e) {
            throw new BadCredentialsException("Credenciales expiradas");
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciales inválidas");
        }
    }

    public DatosUsuario obtenerUsuarioLogeado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario;
        if (principal instanceof Usuario user) {
            usuario = user;
        } else {
            String username = principal.toString();
            usuario = repository.findByNombreUsuario(username)
                    .orElseThrow(() -> new ValidacionException("Usuario no encontrado", HttpStatus.NOT_FOUND));
        }

        return new DatosUsuario(usuario);
    }
}
