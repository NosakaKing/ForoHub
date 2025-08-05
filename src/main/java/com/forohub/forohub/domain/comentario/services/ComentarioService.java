package com.forohub.forohub.domain.comentario.services;


import com.forohub.forohub.domain.comentario.dto.DatosComentario;
import com.forohub.forohub.domain.ValidacionException;
import com.forohub.forohub.domain.comentario.dto.DatosListaComentario;
import com.forohub.forohub.domain.comentario.models.Comentario;
import com.forohub.forohub.domain.comentario.repositories.ComentarioRepository;
import com.forohub.forohub.domain.topico.repositories.TopicoRespository;
import com.forohub.forohub.domain.usuario.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioService {
    private final TopicoRespository topicoRespository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository repository;

    public void registrar(DatosComentario datos, Long idAutor) {
        var comentario = buildComentario(datos, idAutor);
        repository.save(comentario);
    }

    private Comentario buildComentario(DatosComentario datos, Long idAutor) {
        validarDatosComentario(datos, idAutor);
        var topico = topicoRespository.findById(datos.idTopico()).orElseThrow();
        var autor = usuarioRepository.findById(idAutor).orElseThrow();

        return Comentario.builder()
                .mensaje(datos.mensaje())
                .topico(topico)
                .autor(autor)
                .build();
    }

    private void validarDatosComentario(DatosComentario datos, Long idAutor) {
        if(!topicoRespository.existsById(datos.idTopico())) {
            throw new ValidacionException("No existe un topico con el id informado", HttpStatus.NOT_FOUND);
        }
        if(!usuarioRepository.existsById(idAutor)) {
            throw new ValidacionException("No existe un autor con el id informado", HttpStatus.NOT_FOUND);
        }
    }

    public List<DatosListaComentario> obtenerComentarios(Long id) {
        var comentarios = repository.findByTopicoId(id);
        return comentarios.stream().map(DatosListaComentario::new).collect(Collectors.toList());
    }
}
