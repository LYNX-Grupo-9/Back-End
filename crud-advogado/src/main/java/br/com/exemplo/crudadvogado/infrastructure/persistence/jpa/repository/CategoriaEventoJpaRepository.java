package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;

import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.CategoriaEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaEventoJpaRepository extends JpaRepository<CategoriaEventoEntity, Long> {
    Optional<CategoriaEventoEntity> findByNome(String nomeEvento);
    List<CategoriaEventoEntity> findByAdvogadoIdAdvogado(UUID idAdvogado);
    Optional<CategoriaEventoEntity> findById(Long idCategoria);
}
