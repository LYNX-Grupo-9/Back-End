package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CategoriaEventoResponse;
import br.com.exemplo.crudadvogado.core.domain.CategoriaEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarTodasCategoriasUseCaseTest {

    @Mock
    private CategoriaEventoGateway categoriaEventoGateway;

    @InjectMocks
    private BuscarTodasCategoriasUseCase buscarTodasCategoriasUseCase;

    private CategoriaEvento categoria;

    @BeforeEach
    void setup() {
        categoria = new CategoriaEvento();
        categoria.setIdCategoria(1L);
        categoria.setNome("Audiência");
        categoria.setCor("#FF0000");
    }

    // =================================
    // ✅ LISTA COM RESULTADO
    // =================================
    @Test
    void deveRetornarListaDeCategorias() {
        // ARRANGE
        when(categoriaEventoGateway.buscarTodas())
                .thenReturn(List.of(categoria));

        // ACT
        List<CategoriaEventoResponse> response =
                buscarTodasCategoriasUseCase.executar();

        // ASSERT
        assertNotNull(response);
        assertEquals(1, response.size());

        CategoriaEventoResponse dto = response.get(0);
        assertEquals(1L, dto.idCategoria());
        assertEquals("Audiência", dto.nome());
        assertEquals("#FF0000", dto.cor());

        verify(categoriaEventoGateway, times(1)).buscarTodas();
    }

    // =================================
    // ✅ LISTA VAZIA
    // =================================
    @Test
    void deveRetornarListaVaziaQuandoNaoHouverCategorias() {
        // ARRANGE
        when(categoriaEventoGateway.buscarTodas())
                .thenReturn(List.of());

        // ACT
        List<CategoriaEventoResponse> response =
                buscarTodasCategoriasUseCase.executar();

        // ASSERT
        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(categoriaEventoGateway, times(1)).buscarTodas();
    }
}
