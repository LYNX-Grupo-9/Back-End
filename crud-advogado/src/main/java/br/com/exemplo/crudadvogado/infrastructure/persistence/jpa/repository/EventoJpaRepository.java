package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EventoJpaRepository extends JpaRepository<EventoEntity, Long> {
    List<EventoEntity> findByCliente_IdCliente(UUID idCliente);
    List<EventoEntity> findByAdvogado_IdAdvogado(UUID idAdvogado);
    List<EventoEntity> findByAdvogado_IdAdvogadoAndDataReuniaoBetween(UUID idAdvogado, Date startDate, Date endDate);
    List<EventoEntity> findByAdvogado_IdAdvogadoAndDataReuniaoAfterOrDataReuniaoEquals(
            UUID idAdvogado, Date afterDate, Date sameDate);
}
