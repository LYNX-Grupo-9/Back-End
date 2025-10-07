package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.domain.Evento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.EventoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.EventoMapper;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.EventoJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Transactional
public class EventoJpaAdapter implements EventoGateway {

    private final EventoJpaRepository repository;
    private final EventoMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    public EventoJpaAdapter(EventoMapper mapper, EventoJpaRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Evento criar(Evento domain) {
        EventoEntity entity = mapper.toEntity(domain);
        EventoEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Evento> buscarPorId(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Evento> buscarPorAdvogado(UUID idAdvogado) {
        return repository.findByAdvogado_IdAdvogado(idAdvogado)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Evento> buscarPorAdvogadoEPeriodo(UUID idAdvogado, LocalDate dataInicio, LocalDate dataFim) {
        Date dataInicial = java.sql.Date.valueOf(dataInicio);
        Date dataFinal = java.sql.Date.valueOf(dataFim);

        return repository.findByAdvogado_IdAdvogadoAndDataReuniaoBetween(
                        idAdvogado, dataInicial, dataFinal)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Evento> buscarPorAdvogadoEDataAtualOuFutura(UUID idAdvogado) {
        LocalDateTime agora = LocalDateTime.now();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(agora.atZone(ZoneId.systemDefault()).toInstant()));

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dataAtual = calendar.getTime();

        return repository
                .findByAdvogado_IdAdvogadoAndDataReuniaoAfterOrDataReuniaoEquals(
                        idAdvogado, dataAtual, dataAtual)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void desvincularCategoriaDosEventos(Long idCategoria) {
        repository.desvincularCategoriaPorId(idCategoria);
    }
}
