package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.parcela.AtualizarParcelaParcialmenteUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.parcela.CriarParcelaUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.ParcelaJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParcelaBeanConfig {

    @Bean
    public CriarParcelaUseCase criarParcelaUseCase(ParcelaJpaAdapter adapater) {
        return new CriarParcelaUseCase(adapater);
    }

    @Bean
    public AtualizarParcelaParcialmenteUseCase atualizarParcelaParcialmenteUseCase(ParcelaJpaAdapter adapter) {
        return new AtualizarParcelaParcialmenteUseCase(adapter);
    }

}
