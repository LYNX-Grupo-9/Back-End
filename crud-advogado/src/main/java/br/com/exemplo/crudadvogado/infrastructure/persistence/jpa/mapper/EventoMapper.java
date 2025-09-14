package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.Evento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.CategoriaEventoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ClienteEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.EventoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.AdvogadoJpaRepository;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.CategoriaEventoJpaRepository;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ClienteJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    private final AdvogadoJpaRepository advogadoJpaRepository;
    private final ClienteJpaRepository clienteJpaRepository;
    private final CategoriaEventoJpaRepository categoriaEventoJpaRepository;

    @Autowired
    public EventoMapper(AdvogadoJpaRepository advogadoJpaRepository, ClienteJpaRepository clienteJpaRepository, CategoriaEventoJpaRepository categoriaEventoJpaRepository) {
        this.advogadoJpaRepository = advogadoJpaRepository;
        this.clienteJpaRepository = clienteJpaRepository;
        this.categoriaEventoJpaRepository = categoriaEventoJpaRepository;
    }

    public EventoEntity toEntity(Evento domain) {
        if (domain == null) {
            return null;
        }

        var entity = new EventoEntity();
        entity.setIdEvento(domain.getIdEvento());
        entity.setNome(domain.getNome());
        entity.setDescricao(domain.getDescricao());
        entity.setLocal(domain.getLocal());
        entity.setLinkReuniao(domain.getLinkReuniao());
        entity.setDataReuniao(domain.getDataReuniao());
        entity.setHoraInicio(domain.getHoraInicio());
        entity.setHoraFim(domain.getHoraFim());

        AdvogadoEntity advogado = advogadoJpaRepository.findById(domain.getAdvogado())
                .orElseThrow(() -> new EntityNotFoundException("Advogado não encontrado"));
        entity.setAdvogado(advogado);

        ClienteEntity cliente = clienteJpaRepository.findById(domain.getCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        entity.setCliente(cliente);

        CategoriaEventoEntity categoriaEvento = categoriaEventoJpaRepository.findById(domain.getCategoria())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        entity.setCategoria(categoriaEvento);

        return entity;
    }

    public Evento toDomain(EventoEntity entity) {
        if (entity == null) {
            return null;
        }

        var domain = Evento.criarNovo(
                entity.getNome(),
                entity.getDescricao(),
                entity.getLocal(),
                entity.getLinkReuniao(),
                entity.getDataReuniao(),
                entity.getHoraInicio(),
                entity.getHoraFim(),
                entity.getAdvogado().getIdAdvogado(),
                entity.getCliente().getIdCliente(),
                entity.getCategoria().getId()
        );

        return domain;
    }
}
