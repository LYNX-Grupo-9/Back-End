package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ProcessoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProcessoJpaRepository extends JpaRepository<ProcessoEntity, UUID> {
    Long countByClienteIdCliente(UUID clienteId);
    List<ProcessoEntity> findByAdvogadoIdAdvogado(UUID idAdvogado);
    List<ProcessoEntity> findByClienteIdCliente(UUID idCliente);
    Long countByCliente_IdCliente(UUID idCliente);
    Optional<ProcessoEntity> findByNumeroProcesso(String numeroProcesso);
    List<ProcessoEntity> findByStatusIgnoreCaseAndAdvogadoIdAdvogado(String status, UUID idAdvogado);
    List<ProcessoEntity> findByAdvogadoIdAdvogadoOrderByNumeroProcessoAsc(UUID idAdvogado);
    List<ProcessoEntity> findByAdvogadoIdAdvogadoOrderByValorDesc(UUID idAdvogado);
    List<ProcessoEntity> findByAdvogadoIdAdvogadoOrderByStatusAsc(UUID idAdvogado);
    List<ProcessoEntity> findByStatusNotIgnoreCaseAndAdvogadoIdAdvogado(String status, UUID idAdvogado);
    @Query("SELECT p FROM ProcessoEntity p WHERE " +
            "p.advogado.idAdvogado = :idAdvogado AND " +
            "(LOWER(p.numeroProcesso) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(p.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(p.cliente.nome) LIKE LOWER(CONCAT('%', :termo, '%')))")
    List<ProcessoEntity> buscarPorNumeroTituloOuCliente(@Param("termo") String termo,
                                                        @Param("idAdvogado") UUID idAdvogado);
    @Query("SELECT AVG(p.valor) FROM ProcessoEntity p WHERE p.advogado.idAdvogado = :idAdvogado")
    Double calcularValorMedioPorAdvogado(@Param("idAdvogado") UUID idAdvogado);
}
