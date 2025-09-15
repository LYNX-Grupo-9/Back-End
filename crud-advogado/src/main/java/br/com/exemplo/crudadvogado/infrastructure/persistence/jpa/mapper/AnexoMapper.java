package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.Anexo;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.AnexoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ClienteEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.ProcessoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ClienteJpaRepository;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ProcessoJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AnexoMapper {

    private final ClienteJpaRepository clienteJpaRepository;
    private final ProcessoJpaRepository processoJpaRepository;

    @Autowired
    public AnexoMapper(ClienteJpaRepository clienteJpaRepository, ProcessoJpaRepository processoJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
        this.processoJpaRepository = processoJpaRepository;
    }

    public AnexoEntity toEntity(Anexo domain) {
        if(Objects.isNull(domain)){
            return null;
        }

        var entity = new AnexoEntity();
            entity.setIdAnexo(domain.getIdAnexo());
            entity.setNomeAnexo(domain.getNomeAnexo());

        ClienteEntity cliente = clienteJpaRepository.findById(domain.getIdCliente())
                .orElseThrow(
                        () ->    new EntityNotFoundException("Cliente não encontrado")
                );
        entity.setCliente(cliente);

        ProcessoEntity processo = processoJpaRepository.findById(domain.getIdProcesso())
                .orElseThrow(
                        () -> new EntityNotFoundException("Processo não encontrado")
                );
        entity.setProcesso(processo);

        return entity;
    }

    public Anexo toDomain(AnexoEntity entity) {
        if(Objects.isNull(entity)){
            return null;
        }

        return Anexo.criarNovo(
                entity.getIdItem(),
                entity.getNomeAnexo(),
                entity.getCliente().getIdCliente(),
                entity.getProcesso().getIdProcesso()
        );
    }
}
