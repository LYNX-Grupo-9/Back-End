package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.CategoriaEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaEventoJpaRepository extends JpaRepository<CategoriaEventoEntity, Long> {
    Optional<CategoriaEventoEntity> findByNome(String nomeEvento);
    List<CategoriaEventoEntity> findByAdvogadoIdAdvogado(UUID idAdvogado);
    Optional<CategoriaEventoEntity> findById(Long idCategoria);

    @Query("SELECT c.nome, COUNT(c.nome), c.cor " +
            "FROM CategoriaEventoEntity c " +
            "WHERE c.advogado.idAdvogado = :idAdvogado " +
            "GROUP BY c.nome, c.cor")
    List<Object[]> countCategoriasAgrupadasPorNome(@Param("idAdvogado") Long idAdvogado);
}
