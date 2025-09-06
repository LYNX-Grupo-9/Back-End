package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, UUID> {
    List<ClienteEntity> findByAdvogadoIdAdvogado(UUID advogadoId);
    boolean existsByEmail(String email);
    Long countByAdvogadoIdAdvogado(UUID advogadoId);
    List<ClienteEntity> findByAdvogadoIdAdvogadoOrderByNaturalidadeAsc(UUID advogadoId);
    List<ClienteEntity> findByAdvogadoIdAdvogadoOrderByNomeAsc(UUID advogadoId);
    List<ClienteEntity> findByAdvogadoIdAdvogadoOrderByDataNascimentoAsc(UUID advogadoId);
    @Query("SELECT c FROM ClienteEntity c WHERE c.advogado.id = :idAdvogado AND " +
            "(LOWER(c.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(c.telefone) LIKE LOWER(CONCAT('%', :termo, '%')))")
    List<ClienteEntity> buscarPorNomeEmailTelefonePorAdvogado(
            @Param("termo") String termo,
            @Param("advogadoId") UUID advogadoId
    );
}
