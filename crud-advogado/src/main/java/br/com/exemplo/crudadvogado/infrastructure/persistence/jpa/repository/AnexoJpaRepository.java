package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AnexoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnexoJpaRepository extends JpaRepository<AnexoEntity, UUID> {
    List<AnexoEntity> findByIdAnexo(Long idAnexo);
    List<AnexoEntity> findByCliente_IdCliente(UUID idCliente);
    List<AnexoEntity> findByProcesso_IdProcesso(UUID idProcesso);
    boolean existsByIdItem(String idItem);
    @Modifying
    @Query("DELETE FROM AnexoEntity a WHERE a.idItem = :idItem")
    void deleteByIdItem(@Param("idItem") String idItem);
    Optional<AnexoEntity> findByIdItem(String idItem);
}
