package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.evento.CriarEventoUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.ClienteJpaAdapter;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.EventoJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventoBeanConfig {

    @Bean
    public CriarEventoUseCase criarEventoUseCase(EventoJpaAdapter adapter) {
        return new CriarEventoUseCase(adapter);
    }

}
