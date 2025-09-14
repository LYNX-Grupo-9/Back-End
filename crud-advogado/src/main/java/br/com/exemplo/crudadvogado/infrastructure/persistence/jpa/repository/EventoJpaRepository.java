package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EventoJpaRepository extends JpaRepository<EventoEntity, UUID> {
    List<EventoEntity> findByCliente_IdCliente(UUID idCliente);
    List<EventoEntity> findByAdvogadoIdAdvogado(UUID idAdvogado);
    List<EventoEntity> findByAdvogadoIdAdvogadoAndDataReuniaoBetween(UUID idAdvogado, Date startDate, Date endDate);
    List<EventoEntity> findByAdvogadoIdAdvogadoAndDataReuniaoAfterOrDataReuniaoEquals(
            UUID idAdvogado, Date afterDate, Date sameDate);
}
