package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.adapter.gateway.SolicitacaoAgendamentoGateway;
import br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento.BuscarSolicitacaoPorIdUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento.BuscarSolicitacoesPorAdvogadoUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento.CriarSolicitacaoAgendamentoUseCase;
import br.com.exemplo.crudadvogado.core.application.usecase.solicitacaoAgendamento.MarcarComoVisualizadoUseCase;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service.RabbitMQProducerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolicitacaoAgendamentoBeanConfig {

    @Bean
    public CriarSolicitacaoAgendamentoUseCase criarSolicitacaoAgendamentoUseCase(
            SolicitacaoAgendamentoGateway solicitacaoAgendamentoGateway,
            RabbitMQProducerService rabbitMQProducerService) {
        return new CriarSolicitacaoAgendamentoUseCase(solicitacaoAgendamentoGateway, rabbitMQProducerService);
    }

    @Bean
    public MarcarComoVisualizadoUseCase marcarComoVisualizadoUseCase(SolicitacaoAgendamentoGateway gateway) {
        return new MarcarComoVisualizadoUseCase(gateway);
    }

    @Bean
    public BuscarSolicitacaoPorIdUseCase buscarSolicitacaoPorIdUseCase(SolicitacaoAgendamentoGateway gateway) {
        return new BuscarSolicitacaoPorIdUseCase(gateway);
    }

    @Bean
    public BuscarSolicitacoesPorAdvogadoUseCase buscarSolicitacoesPorAdvogadoUseCase(SolicitacaoAgendamentoGateway gateway) {
        return new BuscarSolicitacoesPorAdvogadoUseCase(gateway);
    }

}
