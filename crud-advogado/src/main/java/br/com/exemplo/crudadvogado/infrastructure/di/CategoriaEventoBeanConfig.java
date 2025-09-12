package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento.CriarCategoriaEventoUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.CategoriaEventoJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaEventoBeanConfig {

    @Bean
    public CriarCategoriaEventoUseCase criarCategoriaEventoUseCase(CategoriaEventoJpaAdapter adapter) {
        return new CriarCategoriaEventoUseCase(adapter);
    }

}
