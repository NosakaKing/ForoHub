package com.forohub.forohub.domain.topico.services;
import com.forohub.forohub.domain.ValidacionException;
import com.forohub.forohub.domain.comentario.repositories.ComentarioRepository;
import com.forohub.forohub.domain.curso.repositories.CursoRepository;
import com.forohub.forohub.domain.topico.dto.*;
import com.forohub.forohub.domain.topico.models.EstadoTopico;
import com.forohub.forohub.domain.topico.models.Topico;
import com.forohub.forohub.domain.topico.repositories.TopicoRespository;
import com.forohub.forohub.domain.usuario.repositories.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TopicoService {
    private final TopicoRespository repository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository comentarioRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Page<DatosListaTopico> obtenerTopicos(int page, int size, Long courseId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fechaCreacion"));
        Page<Topico> topicos = (courseId != null)
                ? repository.findByCursoId(courseId, pageable)
                : repository.findAll(pageable);
        return topicos.map(DatosListaTopico::new);
    }

    public DatosUnTopico obtenerTopicoPorId(Long id) {
        var topico = repository.findById(id).orElseThrow(() -> new ValidacionException("Topico no encontrado", HttpStatus.NOT_FOUND));
        var total = comentarioRepository.contarRespuestasPorTopico(id);
        return new DatosUnTopico(topico, total);
    }

    public DatosEditarIdTopico obtenerDatosEditarIdTopico(Long id, Long idAutor) {
        var topico = repository.findById(id).orElseThrow(() -> new ValidacionException("Topico no encontrado", HttpStatus.NOT_FOUND));
        validarEditarTopico(topico, idAutor);
        return new DatosEditarIdTopico(topico);
    }

    public void eliminar(Long id) {
        var eliminar = repository.findById(id).orElseThrow(() -> new ValidacionException("Topico no encontrado", HttpStatus.NOT_FOUND));
        repository.delete(eliminar);
    }

    public DatosDetalleTopico registrar(DatosTopico datos, Long idAutor) {
        var topico = buildTopico(datos, idAutor);
        topico.setEstado(EstadoTopico.NO_RESPONDIDO);
        repository.save(topico);
        entityManager.refresh(topico);
        return new DatosDetalleTopico(topico);
    }

    public DatosDetalleTopico editar(DatosEditarTopico datos, Long idAutor) {
        var topicoId = repository.findById(datos.id()).orElseThrow(() -> new ValidacionException("Topico no encontrado", HttpStatus.NOT_FOUND));
        validarEditarTopico(topicoId, idAutor);
        topicoId.actualizarTopico(datos);
        return new DatosDetalleTopico(topicoId);
    }

    private void validarEditarTopico(Topico topico, Long idAutor) {
        if (!topico.getAutor().getId().equals(idAutor)) {
            throw new ValidacionException("No tienes permisos para editar este tópico", HttpStatus.FORBIDDEN);
        }
    }

    private void validarDatosTopico(DatosTopico dto, Long idAutor) {
        if(!cursoRepository.existsById(dto.idCurso())){
            throw new ValidacionException("No existe un curso con el id informado", HttpStatus.NOT_FOUND);
        }
        if(!usuarioRepository.existsById(idAutor)){
            throw new ValidacionException("No existe un autor con el id informado", HttpStatus.NOT_FOUND);
        }
    }

    private Topico buildTopico(DatosTopico dto, Long idAutor) {
        validarDatosTopico(dto, idAutor);
        var curso = cursoRepository.findById(dto.idCurso()).orElseThrow();
        var autor = usuarioRepository.findById(idAutor).orElseThrow();

        return Topico.builder()
                .titulo(dto.titulo())
                .mensaje(dto.mensaje())
                .curso(curso)
                .autor(autor)
                .build();
    }

    public void marcarSolucion(Long topicId, Long commentId, Long idAutor) {
        var topico = repository.findById(topicId).orElseThrow(() -> new ValidacionException("Topico no encontrado", HttpStatus.NOT_FOUND));
        comentarioRepository.findById(commentId).orElseThrow(() -> new ValidacionException("Comentario no encontrado", HttpStatus.NOT_FOUND));
        validarEditarTopico(topico, idAutor);
        topico.setEstado(EstadoTopico.SOLUCIONADO);
        topico.setSolucionId(commentId);
        repository.save(topico);
    }
}
