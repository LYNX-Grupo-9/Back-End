package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.application.exception.CategoriaEventoNotFoundException;
import br.com.exemplo.crudadvogado.core.domain.CategoriaEvento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarCategoriaUseCaseTest {

    @Mock
    private CategoriaEventoGateway categoriaEventoGateway;

    @Mock
    private EventoGateway eventoGateway;

    @InjectMocks
    private DeletarCategoriaUseCase deletarCategoriaUseCase;

    // ========================================
    // ✅ SUCESSO — DELETA CATEGORIA
    // ========================================
    @Test
    void deveDeletarCategoriaQuandoExistir() {
        // ARRANGE
        Long idCategoria = 1L;

        CategoriaEvento categoria = new CategoriaEvento();
        categoria.setIdCategoria(idCategoria);
        categoria.setNome("Audiência");
        categoria.setCor("#FF0000");

        when(categoriaEventoGateway.buscarPorId(idCategoria))
                .thenReturn(Optional.of(categoria));

        // ACT
        deletarCategoriaUseCase.executar(idCategoria);

        // ASSERT — ordem correta das chamadas
        InOrder ordem = inOrder(categoriaEventoGateway, eventoGateway);

        ordem.verify(categoriaEventoGateway)
                .buscarPorId(idCategoria);

        ordem.verify(eventoGateway)
                .desvincularCategoriaDosEventos(idCategoria);

        ordem.verify(categoriaEventoGateway)
                .deletar(idCategoria);

        verifyNoMoreInteractions(eventoGateway, categoriaEventoGateway);
    }

    // ========================================
    // ❌ ERRO — CATEGORIA NÃO EXISTE
    // ========================================
    @Test
    void deveLancarExcecaoQuandoCategoriaNaoExistir() {
        // ARRANGE
        Long idCategoria = 99L;

        when(categoriaEventoGateway.buscarPorId(idCategoria))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        CategoriaEventoNotFoundException exception =
                assertThrows(
                        CategoriaEventoNotFoundException.class,
                        () -> deletarCategoriaUseCase.executar(idCategoria)
                );

        assertEquals(
                "Categoria não encontrada com ID: 99",
                exception.getMessage()
        );

        verify(categoriaEventoGateway, times(1))
                .buscarPorId(idCategoria);

        verifyNoInteractions(eventoGateway);

        verify(categoriaEventoGateway, never())
                .deletar(anyLong());
    }
}
