package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, UUID> {
    List<ClienteEntity> findByAdvogadoIdAdvogado(UUID idAdvogado);
    boolean existsByEmail(String email);
    Long countByAdvogadoIdAdvogado(UUID idAdvogado);
    List<ClienteEntity> findByAdvogadoIdAdvogadoOrderByNomeAsc(UUID idAdvogado);
    List<ClienteEntity> findByAdvogadoIdAdvogadoOrderByNaturalidadeAsc(UUID idAdvogado);
    List<ClienteEntity> findByAdvogadoIdAdvogadoOrderByDataNascimentoAsc(UUID idAdvogado);
    @Query("SELECT c FROM ClienteEntity c WHERE c.advogado.idAdvogado = :idAdvogado AND " +
            "(LOWER(c.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "c.telefone LIKE CONCAT('%', :termo, '%'))")
    List<ClienteEntity> buscarPorNomeEmailTelefonePorAdvogado(@Param("termo") String termo,
                                                              @Param("idAdvogado") UUID idAdvogado);
    @Query("SELECT c, COUNT(p) as qtdProcessos FROM ClienteEntity c " +
            "LEFT JOIN ProcessoEntity p ON c.idCliente = p.cliente.idCliente " +
            "WHERE c.advogado.idAdvogado = :idAdvogado " +
            "GROUP BY c.idCliente, c.nome, c.email, c.telefone, c.endereco, c.documento, " +
            "c.tipoDocumento, c.genero, c.estadoCivil, c.profissao, c.passaporte, c.cnh, " +
            "c.naturalidade, c.dataNascimento, c.advogado.idAdvogado " +
            "ORDER BY qtdProcessos DESC")
    List<Object[]> findClientesComQuantidadeProcessos(@Param("idAdvogado") UUID idAdvogado);
}
