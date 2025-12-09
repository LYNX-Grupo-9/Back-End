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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarCategoriasPorAdvogadoUseCaseTest {

    @Mock
    private CategoriaEventoGateway categoriaEventoGateway;

    @InjectMocks
    private BuscarCategoriasPorAdvogadoUseCase buscarCategoriasPorAdvogadoUseCase;

    private UUID idAdvogado;
    private CategoriaEvento categoria;

    @BeforeEach
    void setup() {
        idAdvogado = UUID.randomUUID();

        categoria = new CategoriaEvento();
        categoria.setIdCategoria(1L);
        categoria.setNome("Audiência");
        categoria.setCor("#00FF00");
    }

    // =================================
    // ✅ LISTA COM RESULTADO
    // =================================
    @Test
    void deveRetornarListaDeCategoriasPorAdvogado() {
        // ARRANGE
        when(categoriaEventoGateway.buscarPorAdvogadoId(idAdvogado))
                .thenReturn(List.of(categoria));

        // ACT
        List<CategoriaEventoResponse> response =
                buscarCategoriasPorAdvogadoUseCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertEquals(1, response.size());

        CategoriaEventoResponse dto = response.get(0);
        assertEquals(1L, dto.idCategoria());
        assertEquals("Audiência", dto.nome());
        assertEquals("#00FF00", dto.cor());

        verify(categoriaEventoGateway, times(1))
                .buscarPorAdvogadoId(idAdvogado);
    }

    // =================================
    // ✅ LISTA VAZIA
    // =================================
    @Test
    void deveRetornarListaVaziaQuandoNaoHouverCategorias() {
        // ARRANGE
        when(categoriaEventoGateway.buscarPorAdvogadoId(idAdvogado))
                .thenReturn(List.of());

        // ACT
        List<CategoriaEventoResponse> response =
                buscarCategoriasPorAdvogadoUseCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(categoriaEventoGateway, times(1))
                .buscarPorAdvogadoId(idAdvogado);
    }
}
