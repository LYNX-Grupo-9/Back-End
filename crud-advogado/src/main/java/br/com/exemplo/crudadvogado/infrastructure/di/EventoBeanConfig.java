package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.usecase.evento.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventoBeanConfig {

    @Bean
    public CriarEventoUseCase criarEventoUseCase(EventoGateway eventoGateway) {
        return new CriarEventoUseCase(eventoGateway);
    }

    @Bean
    public BuscarEventoPorIdUseCase buscarEventoPorIdUseCase(EventoGateway eventoGateway) {
        return new BuscarEventoPorIdUseCase(eventoGateway);
    }

    @Bean
    public BuscarEventosPorAdvogadoUseCase buscarEventosPorAdvogadoUseCase(EventoGateway eventoGateway) {
        return new BuscarEventosPorAdvogadoUseCase(eventoGateway);
    }

    @Bean
    public BuscarEventosProximosSeteDiasUseCase buscarEventosProximosSeteDiasUseCase(EventoGateway eventoGateway) {
        return new BuscarEventosProximosSeteDiasUseCase(eventoGateway);
    }

    @Bean
    public BuscarProximoEventoUseCase buscarProximoEventoUseCase(EventoGateway eventoGateway) {
        return new BuscarProximoEventoUseCase(eventoGateway);
    }

    @Bean
    public DeletarEventoUseCase deletarEventoUseCase(EventoGateway eventoGateway) {
        return new DeletarEventoUseCase(eventoGateway);
    }
}
