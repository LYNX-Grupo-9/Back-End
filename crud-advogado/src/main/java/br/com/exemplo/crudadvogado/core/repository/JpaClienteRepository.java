package br.com.exemplo.crudadvogado.core.repository;

import br.com.exemplo.crudadvogado.core.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaClienteRepository extends JpaRepository<Cliente, UUID> {
}
