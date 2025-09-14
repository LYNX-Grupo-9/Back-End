package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.cliente.CriarClienteUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.ClienteJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteBeanConfig {

    @Bean
    public CriarClienteUseCase criarClienteUseCase(ClienteJpaAdapter adapter) {
        return new CriarClienteUseCase(adapter);
    }

}
