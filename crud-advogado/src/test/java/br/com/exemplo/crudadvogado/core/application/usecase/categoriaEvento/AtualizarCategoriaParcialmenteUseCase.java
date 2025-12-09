package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.categoriaEvento.AtualizarCategoriaCommand;
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
class AtualizarCategoriaParcialmenteUseCaseTest {

    @Mock
    private CategoriaEventoGateway categoriaEventoGateway;

    @InjectMocks
    private AtualizarCategoriaParcialmenteUseCase atualizarCategoriaUseCase;

    private CategoriaEvento categoriaExistente;

    @BeforeEach
    void setup() {
        categoriaExistente = new CategoriaEvento();
        categoriaExistente.setIdCategoria(1L);
        categoriaExistente.setNome("Antigo Nome");
        categoriaExistente.setCor("#000000");
    }

    // =====================================
    // ✅ ATUALIZA NOME E COR
    // =====================================
    @Test
    void deveAtualizarNomeECorDaCategoria() {
        // ARRANGE
        AtualizarCategoriaCommand command =
                new AtualizarCategoriaCommand("Novo Nome", "#FFFFFF");

        when(categoriaEventoGateway.buscarPorId(1L))
                .thenReturn(Optional.of(categoriaExistente));

        when(categoriaEventoGateway.atualizar(any(CategoriaEvento.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        CategoriaEventoResponse response =
                atualizarCategoriaUseCase.executar(1L, command);

        // ASSERT
        assertNotNull(response);
        assertEquals(1L, response.idCategoria());
        assertEquals("Novo Nome", response.nome());
        assertEquals("#FFFFFF", response.cor());

        verify(categoriaEventoGateway, times(1))
                .buscarPorId(1L);
        verify(categoriaEventoGateway, times(1))
                .atualizar(any(CategoriaEvento.class));
    }

    // =====================================
    // ✅ ATUALIZA APENAS UM CAMPO
    // =====================================
    @Test
    void deveAtualizarApenasONome() {
        // ARRANGE
        AtualizarCategoriaCommand command =
                new AtualizarCategoriaCommand("Somente Nome", null);

        when(categoriaEventoGateway.buscarPorId(1L))
                .thenReturn(Optional.of(categoriaExistente));

        when(categoriaEventoGateway.atualizar(any(CategoriaEvento.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        CategoriaEventoResponse response =
                atualizarCategoriaUseCase.executar(1L, command);

        // ASSERT
        assertEquals("Somente Nome", response.nome());
        assertEquals("#000000", response.cor()); // cor antiga mantida

        verify(categoriaEventoGateway, times(1))
                .atualizar(any(CategoriaEvento.class));
    }

    // =====================================
    // ❌ CATEGORIA NÃO ENCONTRADA
    // =====================================
    @Test
    void deveLancarExcecaoQuandoCategoriaNaoExistir() {
        // ARRANGE
        AtualizarCategoriaCommand command =
                new AtualizarCategoriaCommand("Teste", "#111111");

        when(categoriaEventoGateway.buscarPorId(99L))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        CategoriaEventoNotFoundException exception = assertThrows(
                CategoriaEventoNotFoundException.class,
                () -> atualizarCategoriaUseCase.executar(99L, command)
        );

        assertEquals(
                "Categoria não encontrada com ID: 99",
                exception.getMessage()
        );

        verify(categoriaEventoGateway, never())
                .atualizar(any());
    }
}
