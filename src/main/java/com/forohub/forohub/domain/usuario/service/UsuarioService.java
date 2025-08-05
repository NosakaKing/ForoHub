package com.forohub.forohub.domain.usuario.service;
import com.forohub.forohub.domain.ValidacionException;
import com.forohub.forohub.domain.usuario.dto.DatosNuevoUsuario;
import com.forohub.forohub.domain.usuario.models.Rol;
import com.forohub.forohub.domain.usuario.models.UserRoles;
import com.forohub.forohub.domain.usuario.models.Usuario;
import com.forohub.forohub.domain.usuario.repositories.RolRepository;
import com.forohub.forohub.domain.usuario.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.passay.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository repository;
    private final RolRepository rolRepository;

    public void registrarUsuario(DatosNuevoUsuario datos) {
        var usuario = buildUsuario(datos);
        usuario.setRol(getAuthority());
        repository.save(usuario);
    }

    private Rol getAuthority() {
        return rolRepository.findByRol(UserRoles.USER)
                .orElseThrow(() -> new ValidacionException("Rol no encontrado", HttpStatus.NOT_FOUND));
    }

    private void validarUsuario(DatosNuevoUsuario datos) {
        if(repository.existsByNombreUsuario(datos.nombreUsuario())) {
            throw new ValidacionException("Usuario ya existente", HttpStatus.BAD_REQUEST);
        }
        if (repository.existsByEmail(datos.email())) {
            throw new ValidacionException("Email ya existente", HttpStatus.BAD_REQUEST);
        }
        if (validarPassword(datos.contrasenia())) {
            throw new ValidacionException("La contraseña debe ser entre 8 a 20 digitos y contener al menos una letra mayuscula, minuscula, un digito y un caracter especial", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validarPassword(String password) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 20),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule()));
        RuleResult result = validator.validate(new PasswordData(password));
        return !result.isValid();
    }
    private Usuario buildUsuario(DatosNuevoUsuario datos) {
        validarUsuario(datos);
        return Usuario.builder()
                .nombre(datos.nombre())
                .apellido(datos.apellido())
                .email(datos.email())
                .nombreUsuario(datos.nombreUsuario())
                .contrasenia(passwordEncoder.encode(datos.contrasenia()))
                .build();
    }
}
