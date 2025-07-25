package com.forohub.forohub.domain.topico.services;
import com.forohub.forohub.domain.ValidacionException;
import com.forohub.forohub.domain.curso.repositories.CursoRepository;
import com.forohub.forohub.domain.topico.dto.DatosDetalleTopico;
import com.forohub.forohub.domain.topico.dto.DatosEditarTopico;
import com.forohub.forohub.domain.topico.dto.DatosListaTopico;
import com.forohub.forohub.domain.topico.dto.DatosTopico;
import com.forohub.forohub.domain.topico.models.Topico;
import com.forohub.forohub.domain.topico.repositories.TopicoRespository;
import com.forohub.forohub.domain.usuario.repositories.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TopicoService {
    private final TopicoRespository respository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Page<DatosListaTopico> obtenerTopicos(int page, int size) {
        var topicos = respository.findAll(PageRequest.of(page, size));
        return topicos.map(DatosListaTopico::new);
    }

    public DatosListaTopico obtenerTopicoPorId(Long id) {
        var topico = respository.findById(id).orElseThrow(() -> new ValidacionException("Topico no encontrado", HttpStatus.NOT_FOUND));
        return new DatosListaTopico(topico);
    }

    public void eliminar(Long id) {
        var eliminar = respository.findById(id).orElseThrow(() -> new ValidacionException("Topico no encontrado", HttpStatus.NOT_FOUND));
        respository.delete(eliminar);
    }

    public DatosDetalleTopico registrar(DatosTopico datos) {
        var topico = buildTopico(datos);
        respository.save(topico);
        entityManager.refresh(topico);
        return new DatosDetalleTopico(topico);
    }

    public DatosDetalleTopico editar(DatosEditarTopico datos) {
        var topicoId = respository.findById(datos.id()).orElseThrow(() -> new ValidacionException("Topico no encontrado", HttpStatus.NOT_FOUND));
        topicoId.actualizarTopico(datos);
        return new DatosDetalleTopico(topicoId);
    }

    private void validarDatosTopico(DatosTopico dto) {
        if(!cursoRepository.existsById(dto.idCurso())){
            throw new ValidacionException("No existe un curso con el id informado", HttpStatus.NOT_FOUND);
        }
        if(!usuarioRepository.existsById(dto.idAutor())){
            throw new ValidacionException("No existe un autor con el id informado", HttpStatus.NOT_FOUND);
        }
    }

    private Topico buildTopico(DatosTopico dto) {
        validarDatosTopico(dto);
        var curso = cursoRepository.findById(dto.idCurso()).orElseThrow();
        var autor = usuarioRepository.findById(dto.idAutor()).orElseThrow();

        return Topico.builder()
                .titulo(dto.titulo())
                .mensaje(dto.mensaje())
                .estado(dto.estado())
                .curso(curso)
                .autor(autor)
                .build();
    }

}
