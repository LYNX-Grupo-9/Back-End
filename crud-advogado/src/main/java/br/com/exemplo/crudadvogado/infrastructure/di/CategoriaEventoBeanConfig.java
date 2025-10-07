package br.com.exemplo.crudadvogado.infrastructure.di;

import br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento.*;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.CategoriaEventoJpaAdapter;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.EventoJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaEventoBeanConfig {

    @Bean
    public CriarCategoriaEventoUseCase criarCategoriaEventoUseCase(CategoriaEventoJpaAdapter adapter) {
        return new CriarCategoriaEventoUseCase(adapter);
    }

    @Bean
    public BuscarTodasCategoriasUseCase buscarTodasCategoriasUseCase(CategoriaEventoJpaAdapter adapter) {
        return new BuscarTodasCategoriasUseCase(adapter);
    }

    @Bean
    public BuscarCategoriasPorAdvogadoUseCase buscarCategoriasPorAdvogadoUseCase(CategoriaEventoJpaAdapter adapter) {
        return new BuscarCategoriasPorAdvogadoUseCase(adapter);
    }

    @Bean
    public BuscarCategoriaPorIdUseCase buscarCategoriaPorIdUseCase(CategoriaEventoJpaAdapter adapter) {
        return new BuscarCategoriaPorIdUseCase(adapter);
    }

    @Bean
    public DeletarCategoriaUseCase deletarCategoriaUseCase(
            CategoriaEventoJpaAdapter categoriaEventoAdapter,
            EventoJpaAdapter eventoAdapter) {
        return new DeletarCategoriaUseCase(categoriaEventoAdapter, eventoAdapter);
    }

    @Bean
    public AtualizarCategoriaParcialmenteUseCase atualizarCategoriaParcialmenteUseCase(
            CategoriaEventoJpaAdapter categoriaEventoAdapter) {
        return new AtualizarCategoriaParcialmenteUseCase(categoriaEventoAdapter);
    }

    @Bean
    public ContarCategoriasPorNomeUseCase contarCategoriasPorNomeUseCase(
            CategoriaEventoJpaAdapter categoriaEventoAdapter) {
        return new ContarCategoriasPorNomeUseCase(categoriaEventoAdapter);
    }
}
