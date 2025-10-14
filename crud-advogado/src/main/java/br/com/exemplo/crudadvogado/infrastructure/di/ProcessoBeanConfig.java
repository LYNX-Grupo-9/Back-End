package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.processo.*;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.ProcessoJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessoBeanConfig {

    @Bean
    public CriarProcessoUseCase criarProcessoUseCase(ProcessoJpaAdapter adapter) {
        return new CriarProcessoUseCase(adapter);
    }

    @Bean
    public ListarProcessosPorAdvogadoUseCase listarProcessosPorAdvogadoUseCase(ProcessoJpaAdapter adapter) {
        return new ListarProcessosPorAdvogadoUseCase(adapter);
    }

    @Bean
    public BuscarProcessoPorIdUseCase buscarProcessoPorIdUseCase(ProcessoJpaAdapter adapter) {
        return new BuscarProcessoPorIdUseCase(adapter);
    }

    @Bean
    public ListarProcessosPorClienteUseCase listarProcessosPorClienteUseCase(ProcessoJpaAdapter adapter) {
        return new ListarProcessosPorClienteUseCase(adapter);
    }

    @Bean
    public ExcluirProcessoPorIdUseCase excluirProcessoPorIdUseCase(ProcessoJpaAdapter adapter) {
        return new ExcluirProcessoPorIdUseCase(adapter);
    }

    @Bean
    public ListarProcessosAtivosPorAdvogadoUseCase listarProcessosAtivosPorAdvogadoUseCase(ProcessoJpaAdapter adapter) {
        return new ListarProcessosAtivosPorAdvogadoUseCase(adapter);
    }

    @Bean
    public ContarProcessosPorStatusPorAdvogadoUseCase contarProcessosPorStatusPorAdvogadoUseCase(ProcessoJpaAdapter adapter) {
        return new ContarProcessosPorStatusPorAdvogadoUseCase(adapter);
    }

    @Bean
    public BuscarProcessosPorTextoUseCase buscarProcessosPorTextoUseCase(ProcessoJpaAdapter adapter) {
        return new BuscarProcessosPorTextoUseCase(adapter);
    }

    @Bean
    public ListarProcessosOrdenadosPorStatusUseCase listarProcessosOrdenadosPorStatusUseCase(ProcessoJpaAdapter adapter) {
        return new ListarProcessosOrdenadosPorStatusUseCase(adapter);
    }

    @Bean
    public CalcularValorMedioProcessosUseCase calcularValorMedioProcessosUseCase(ProcessoJpaAdapter adapter) {
        return new CalcularValorMedioProcessosUseCase(adapter);
    }

    @Bean
    public ListarProcessosOrdenadosPorValorUseCase listarProcessosOrdenadosPorValorUseCase(ProcessoJpaAdapter adapter) {
        return new ListarProcessosOrdenadosPorValorUseCase(adapter);
    }

    @Bean
    public ContarProcessosPorClasseProcessualUseCase contarProcessosPorClasseProcessualUseCase(ProcessoJpaAdapter adapter) {
        return new ContarProcessosPorClasseProcessualUseCase(adapter);
    }

    @Bean
    public ListarProcessosOrdenadosPorNomeClienteUseCase listarProcessosOrdenadosPorNomeClienteUseCase(ProcessoJpaAdapter adapter) {
        return new ListarProcessosOrdenadosPorNomeClienteUseCase(adapter);
    }

    @Bean
    public ListarProcessosOrdenadosPorNumeroProcessoUseCase listarProcessosOrdenadosPorNumeroProcessoUseCase(ProcessoJpaAdapter adapter) {
        return new ListarProcessosOrdenadosPorNumeroProcessoUseCase(adapter);
    }

    @Bean
    public AtualizarProcessoParcialmenteUseCase atualizarProcessoParcialmenteUseCase(ProcessoJpaAdapter adapter) {
        return new AtualizarProcessoParcialmenteUseCase(adapter);
    }
}
