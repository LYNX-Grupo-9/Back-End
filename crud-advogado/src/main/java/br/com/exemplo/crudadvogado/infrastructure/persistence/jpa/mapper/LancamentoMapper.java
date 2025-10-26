package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper;

import br.com.exemplo.crudadvogado.core.domain.Lancamento;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.entity.LancamentoEntity;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ClienteJpaRepository;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.repository.ProcessoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LancamentoMapper {

    private final ClienteJpaRepository clienteJpaRepository;
    private final ProcessoJpaRepository processoJpaRepository;

    @Autowired
    public LancamentoMapper(ClienteJpaRepository clienteJpaRepository, ProcessoJpaRepository processoJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
        this.processoJpaRepository = processoJpaRepository;
    }

    public LancamentoEntity toEntity(Lancamento domain) {
        if(domain == null){
            return null;
        }

        var entity = new LancamentoEntity();
        entity.setIdLancamento(domain.getIdLancamento());
        entity.setTitulo(domain.getTitulo());

        if(domain.getIdCliente() != null) {
            var cliente = clienteJpaRepository.findById(domain.getIdCliente())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
            entity.setCliente(cliente);
        } else {
            entity.setCliente(null);
        }

        if(domain.getIdProcesso() != null) {
            var processo = processoJpaRepository.findById(domain.getIdProcesso())
                    .orElseThrow(() -> new IllegalArgumentException("Processo não encontrado"));
            entity.setProcesso(processo);
        } else {
            entity.setProcesso(null);
        }

        return entity;
    }

    public Lancamento toDomain(LancamentoEntity entity) {
        if(entity == null){
            return null;
        }

        var domain = new Lancamento(
                entity.getTitulo(),
                entity.getProcesso() != null ? entity.getProcesso().getIdProcesso() : null,
                entity.getCliente() != null ? entity.getCliente().getIdCliente() : null
        );
        domain.setIdLancamento(entity.getIdLancamento());

        return domain;
    }
}
