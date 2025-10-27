package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento.CriarSolicitacaoAgendamentoUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service.RabbitMQProducerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public CriarSolicitacaoAgendamentoUseCase criarSolicitacaoAgendamentoUseCase(
            SolicitacaoAgendamentoGateway solicitacaoAgendamentoGateway,
            RabbitMQProducerService rabbitMQProducerService) {
        return new CriarSolicitacaoAgendamentoUseCase(solicitacaoAgendamentoGateway, rabbitMQProducerService);
    }
}
