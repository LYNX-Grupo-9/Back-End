package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.Evento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.*;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.AdvogadoJpaRepository;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.CategoriaEventoJpaRepository;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ClienteJpaRepository;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ProcessoJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventoMapper {

    private final AdvogadoJpaRepository advogadoJpaRepository;
    private final ClienteJpaRepository clienteJpaRepository;
    private final CategoriaEventoJpaRepository categoriaEventoJpaRepository;
    private final ProcessoJpaRepository processoJpaRepository;

    @Autowired
    public EventoMapper(AdvogadoJpaRepository advogadoJpaRepository, ClienteJpaRepository clienteJpaRepository, CategoriaEventoJpaRepository categoriaEventoJpaRepository, ProcessoJpaRepository processoJpaRepository) {
        this.advogadoJpaRepository = advogadoJpaRepository;
        this.clienteJpaRepository = clienteJpaRepository;
        this.categoriaEventoJpaRepository = categoriaEventoJpaRepository;
        this.processoJpaRepository = processoJpaRepository;
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

        if (domain.getAdvogado() != null) {
            AdvogadoEntity advogado = advogadoJpaRepository.findById(domain.getAdvogado())
                    .orElseThrow(() -> new EntityNotFoundException("Advogado não encontrado"));
            entity.setAdvogado(advogado);
        } else {
            entity.setAdvogado(null);
        }

        if (domain.getCliente() != null) {
            ClienteEntity cliente = clienteJpaRepository.findById(domain.getCliente())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
            entity.setCliente(cliente);
        } else {
            entity.setCliente(null);
        }

        // Categoria
        if (domain.getCategoria() != null) {
            CategoriaEventoEntity categoriaEvento = categoriaEventoJpaRepository.findById(domain.getCategoria())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
            entity.setCategoria(categoriaEvento);
        } else {
            entity.setCategoria(null);
        }

        if (domain.getProcesso() != null) {
            ProcessoEntity processo = processoJpaRepository.findById(domain.getProcesso())
                    .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado"));
            entity.setProcesso(processo);
        } else {
            entity.setProcesso(null);
        }

        return entity;
    }

    public Evento toDomain(EventoEntity entity) {
        if (entity == null) {
            return null;
        }

        UUID advogadoId = entity.getAdvogado() != null ? entity.getAdvogado().getIdAdvogado() : null;
        UUID clienteId = entity.getCliente() != null ? entity.getCliente().getIdCliente() : null;
        Long categoriaId = entity.getCategoria() != null ? entity.getCategoria().getId() : null;
        UUID processoId = entity.getProcesso() != null ? entity.getProcesso().getIdProcesso() : null;

        var domain = Evento.criarExistente(
                entity.getIdEvento(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getLocal(),
                entity.getLinkReuniao(),
                entity.getDataReuniao(),
                entity.getHoraInicio(),
                entity.getHoraFim(),
                advogadoId,
                clienteId,
                categoriaId,
                processoId
        );

        return domain;
    }
}