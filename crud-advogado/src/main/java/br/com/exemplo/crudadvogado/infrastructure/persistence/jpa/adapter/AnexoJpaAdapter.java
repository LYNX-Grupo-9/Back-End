package br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AnexoGateway;
import br.com.exemplo.crudadvogado.core.application.usecase.anexo.CriarAnexoUseCase;
import br.com.exemplo.crudadvogado.core.domain.Anexo;
import org.springframework.stereotype.Repository;

@Repository
public class AnexoJpaAdapter implements AnexoGateway {


    @Override
    public Anexo criar(Anexo domain) {
        return null;
    }
}
