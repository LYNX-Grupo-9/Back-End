package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AnexoGateway;
import br.com.exemplo.crudadvogado.core.application.usecase.anexo.DeletarAnexoUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.anexo.BuscarAnexoUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.anexo.CriarAnexoUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.AnexoJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnexoBeanConfig {

    @Bean
    public CriarAnexoUseCase  criarAnexoUseCase(AnexoJpaAdapter adapter) {
        return new CriarAnexoUseCase(adapter);
    }

    @Bean
    public BuscarAnexoUseCase buscarAnexoUseCase(AnexoGateway anexoGateway) {
        return new BuscarAnexoUseCase(anexoGateway);
    }

    @Bean
    public DeletarAnexoUseCase deletarAnexoUseCase(AnexoGateway anexoGateway) {
        return new DeletarAnexoUseCase(anexoGateway);
    }
}