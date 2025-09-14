package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ProcessoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProcessoJpaRepository extends JpaRepository<ProcessoEntity, UUID> {
    List<ProcessoEntity> findByAdvogadoIdAdvogado(UUID idAdvogado);
    Long countByCliente_IdCliente(UUID idCliente);
    Optional<ProcessoEntity> findByNumeroProcesso(String numeroProcesso);
    List<ProcessoEntity> findByStatusIgnoreCaseAndAdvogadoIdAdvogado(String status, UUID idAdvogado);
    List<ProcessoEntity> findByAdvogadoIdAdvogadoOrderByNumeroProcessoAsc(UUID idAdvogado);
    List<ProcessoEntity> findByAdvogadoIdAdvogadoOrderByValorDesc(UUID idAdvogado);
    List<ProcessoEntity> findByAdvogadoIdAdvogadoOrderByStatusAsc(UUID idAdvogado);
    List<ProcessoEntity> findByStatusNotIgnoreCaseAndAdvogadoIdAdvogado(String status, UUID idAdvogado);
}
