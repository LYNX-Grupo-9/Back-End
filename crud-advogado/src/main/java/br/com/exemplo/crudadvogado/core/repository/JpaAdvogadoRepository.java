package br.com.exemplo.crudadvogado.core.repository;

import br.com.exemplo.crudadvogado.core.domain.Advogado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaAdvogadoRepository extends JpaRepository<Advogado, UUID> {
}
