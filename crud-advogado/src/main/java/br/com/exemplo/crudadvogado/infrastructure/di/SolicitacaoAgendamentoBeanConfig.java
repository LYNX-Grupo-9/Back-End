package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento.CriarSolicitacaoAgendamentoUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.SolicitacaoAgendamentoJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolicitacaoAgendamentoBeanConfig {

    @Bean
    public CriarSolicitacaoAgendamentoUseCase criarSolitacaoAgendamentoUseCase(SolicitacaoAgendamentoJpaAdapter adapter) {
        return new CriarSolicitacaoAgendamentoUseCase(adapter);
    }

}
