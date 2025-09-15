package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository;


import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdvogadoJpaRepository extends JpaRepository<AdvogadoEntity, UUID> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByRegistroOab(String registroOab);
    boolean existsByIdAdvogado(UUID idAdvogado);
    Optional<AdvogadoEntity> findByEmail(String email);
    Optional<AdvogadoEntity> findByNome(String nome);
}
