package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.cliente.*;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.AdvogadoJpaAdapter;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.ClienteJpaAdapter;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.ProcessoJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteBeanConfig {

    @Bean
    public CriarClienteUseCase criarClienteUseCase(ClienteJpaAdapter adapter) {
        return new CriarClienteUseCase(adapter);
    }

    @Bean
    public ListarClientesPorAdvogadoUseCase listarClientesPorAdvogadoUseCase(
            ClienteJpaAdapter clienteAdapter,
            AdvogadoJpaAdapter advogadoAdapter) {
        return new ListarClientesPorAdvogadoUseCase(clienteAdapter, advogadoAdapter);
    }

    @Bean
    public AtualizarClienteUseCase atualizarClienteUseCase(
            ClienteJpaAdapter clienteAdapter) {
        return new AtualizarClienteUseCase(clienteAdapter);
    }
    @Bean
    public ContarClientesPorAdvogadoUseCase contarClientesPorAdvogadoUseCase(
            ClienteJpaAdapter clienteAdapter,
            AdvogadoJpaAdapter advogadoAdapter) {
        return new ContarClientesPorAdvogadoUseCase(clienteAdapter, advogadoAdapter);
    }

    @Bean
    public BuscarClientesPorTextoUseCase buscarClientesPorTextoUseCase(
            ClienteJpaAdapter clienteAdapter,
            AdvogadoJpaAdapter advogadoAdapter) {
        return new BuscarClientesPorTextoUseCase(clienteAdapter, advogadoAdapter);
    }

    @Bean
    public ListarClientesOrdenadosPorProcessosUseCase listarClientesOrdenadosPorProcessosUseCase(
            ClienteJpaAdapter clienteAdapter,
            AdvogadoJpaAdapter advogadoAdapter,
            ProcessoJpaAdapter processoAdapter) {
        return new ListarClientesOrdenadosPorProcessosUseCase(clienteAdapter, advogadoAdapter, processoAdapter);
    }
}
