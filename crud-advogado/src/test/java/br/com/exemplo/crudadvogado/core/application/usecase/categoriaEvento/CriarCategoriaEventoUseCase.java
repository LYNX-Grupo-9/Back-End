package br.com.exemplo.crudadvogado.core.application.usecase.categoriaEvento;

import br.com.exemplo.crudadvogado.core.adapter.gateway.CategoriaEventoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.categoriaEvento.CriarCategoriaEventoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.categoriaEvento.CriarCategoriaEventoResponse;
import br.com.exemplo.crudadvogado.core.domain.CategoriaEvento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarCategoriaEventoUseCaseTest {

    @Mock
    private CategoriaEventoGateway categoriaEventoGateway;

    @InjectMocks
    private CriarCategoriaEventoUseCase criarCategoriaEventoUseCase;

    // ========================================
    // ✅ SUCESSO — CRIAR CATEGORIA
    // ========================================
    @Test
    void deveCriarCategoriaEventoComSucesso() {
        // ARRANGE
        UUID idAdvogado = UUID.randomUUID();

        CriarCategoriaEventoCommand command =
                new CriarCategoriaEventoCommand(
                        "Audiência",
                        "#FF0000",
                        idAdvogado
                );

        CategoriaEvento categoriaCriada = new CategoriaEvento();
        categoriaCriada.setIdCategoria(1L);
        categoriaCriada.setNome("Audiência");
        categoriaCriada.setCor("#FF0000");

        when(categoriaEventoGateway.criar(any(CategoriaEvento.class)))
                .thenReturn(categoriaCriada);

        // ACT
        CriarCategoriaEventoResponse response =
                criarCategoriaEventoUseCase.executar(command);

        // ASSERT
        assertNotNull(response);
        assertEquals(1L, response.idCategoria());
        assertEquals("Audiência", response.nome());
        assertEquals("#FF0000", response.cor());

        // Captura o objeto enviado ao gateway
        ArgumentCaptor<CategoriaEvento> captor =
                ArgumentCaptor.forClass(CategoriaEvento.class);

        verify(categoriaEventoGateway).criar(captor.capture());

        CategoriaEvento enviado = captor.getValue();
        assertEquals("Audiência", enviado.getNome());
        assertEquals("#FF0000", enviado.getCor());
        assertEquals(idAdvogado, enviado.getIdAdvogado());
    }
}
