package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CategoriaDetalhesResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.ContarCategoriasResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContarCategoriasPorNomeUseCaseTest {

    @Mock
    private CategoriaEventoGateway categoriaEventoGateway;

    @InjectMocks
    private ContarCategoriasPorNomeUseCase contarCategoriasPorNomeUseCase;

    // ========================================
    // ✅ SUCESSO — COM RESULTADOS
    // ========================================
    @Test
    void deveContarCategoriasAgrupadasPorNome() {
        // ARRANGE
        UUID idAdvogado = UUID.randomUUID();

        List<Object[]> retornoMock = List.of(
                new Object[]{"Audiência", 5L, "#FF0000"},
                new Object[]{"Reunião", 2L, "#00FF00"}
        );

        when(categoriaEventoGateway
                .contarCategoriasAgrupadasPorNome(idAdvogado))
                .thenReturn(retornoMock);

        // ACT
        ContarCategoriasResponse response =
                contarCategoriasPorNomeUseCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        Map<String, CategoriaDetalhesResponse> mapa =
                response.categorias();

        assertEquals(2, mapa.size());

        CategoriaDetalhesResponse audiencia = mapa.get("Audiência");
        assertEquals(5L, audiencia.quantidade());
        assertEquals("#FF0000", audiencia.cor());

        CategoriaDetalhesResponse reuniao = mapa.get("Reunião");
        assertEquals(2L, reuniao.quantidade());
        assertEquals("#00FF00", reuniao.cor());

        verify(categoriaEventoGateway, times(1))
                .contarCategoriasAgrupadasPorNome(idAdvogado);
    }

    // ========================================
    // ✅ LISTA VAZIA
    // ========================================
    @Test
    void deveRetornarMapaVazioQuandoNaoHouverCategorias() {
        // ARRANGE
        UUID idAdvogado = UUID.randomUUID();

        when(categoriaEventoGateway
                .contarCategoriasAgrupadasPorNome(idAdvogado))
                .thenReturn(List.of());

        // ACT
        ContarCategoriasResponse response =
                contarCategoriasPorNomeUseCase.executar(idAdvogado);

        // ASSERT
        assertNotNull(response);
        assertTrue(response.categorias().isEmpty());

        verify(categoriaEventoGateway, times(1))
                .contarCategoriasAgrupadasPorNome(idAdvogado);
    }
}
