package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.adapter.gateway.LancamentoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.usecase.lancamento.BuscarLancamentosPorAdvogadoUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.lancamento.CriarLancamentoUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.LancamentoJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LancamentoBeanConfig {

    @Bean
    public CriarLancamentoUseCase criarLancamentoUseCase(LancamentoJpaAdapter adapter){
        return new CriarLancamentoUseCase(adapter);
    }

    @Bean
    public BuscarLancamentosPorAdvogadoUseCase buscarLancamentosPorAdvogadoUseCase(
            LancamentoGateway lancamentoGateway,
            ProcessoGateway processoGateway) {
        return new BuscarLancamentosPorAdvogadoUseCase(lancamentoGateway, processoGateway);
    }
}
