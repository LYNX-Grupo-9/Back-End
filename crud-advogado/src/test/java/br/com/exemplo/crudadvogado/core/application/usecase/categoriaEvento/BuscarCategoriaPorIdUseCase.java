package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CategoriaEventoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.CategoriaEventoNotFoundException;
import br.com.exemplo.crudadvogado.core.domain.CategoriaEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarCategoriaPorIdUseCaseTest {

    @Mock
    private CategoriaEventoGateway categoriaEventoGateway;

    @InjectMocks
    private BuscarCategoriaPorIdUseCase buscarCategoriaPorIdUseCase;

    private CategoriaEvento categoria;

    @BeforeEach
    void setup() {
        categoria = new CategoriaEvento();
        categoria.setIdCategoria(1L);
        categoria.setNome("Audiência");
        categoria.setCor("#FF0000");
    }

    // ===========================
    // ✅ CATEGORIA EXISTE
    // ===========================
    @Test
    void deveRetornarCategoriaQuandoExistir() {
        // ARRANGE
        when(categoriaEventoGateway.buscarPorId(1L))
                .thenReturn(Optional.of(categoria));

        // ACT
        CategoriaEventoResponse response =
                buscarCategoriaPorIdUseCase.executar(1L);

        // ASSERT
        assertNotNull(response);
        assertEquals(1L, response.idCategoria());
        assertEquals("Audiência", response.nome());
        assertEquals("#FF0000", response.cor());

        verify(categoriaEventoGateway, times(1))
                .buscarPorId(1L);
    }

    // ===========================
    // ❌ CATEGORIA NÃO EXISTE
    // ===========================
    @Test
    void deveLancarExcecaoQuandoCategoriaNaoExistir() {
        // ARRANGE
        when(categoriaEventoGateway.buscarPorId(99L))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        CategoriaEventoNotFoundException exception = assertThrows(
                CategoriaEventoNotFoundException.class,
                () -> buscarCategoriaPorIdUseCase.executar(99L)
        );

        assertEquals(
                "Categoria não encontrada com ID: 99",
                exception.getMessage()
        );

        verify(categoriaEventoGateway, times(1))
                .buscarPorId(99L);
    }
}
