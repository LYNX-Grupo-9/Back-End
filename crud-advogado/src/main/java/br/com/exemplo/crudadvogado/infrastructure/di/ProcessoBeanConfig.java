package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.processo.CriarProcessoUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.ProcessoJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessoBeanConfig {

    @Bean
    public CriarProcessoUseCase criarProcessoUseCase(ProcessoJpaAdapter adapter) {
        return new CriarProcessoUseCase(adapter);
    }

}
