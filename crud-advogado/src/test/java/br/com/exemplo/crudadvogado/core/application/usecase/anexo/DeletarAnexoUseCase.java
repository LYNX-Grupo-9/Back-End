package br.com.exemplo.crudadvogado.core.application.usecase.anexo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AnexoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarAnexoUseCaseTest {

    @Mock
    private AnexoGateway anexoGateway;

    @InjectMocks
    private DeletarAnexoUseCase deletarAnexoUseCase;

    // ===========================
    // ✅ SUCESSO: ANEXO EXISTE
    // ===========================
    @Test
    void deveDeletarAnexoQuandoIdExistir() {
        // ARRANGE
        String idItem = "123";

        when(anexoGateway.existePorIdItem(idItem))
                .thenReturn(true);

        // ACT
        deletarAnexoUseCase.executar(idItem);

        // ASSERT
        verify(anexoGateway, times(1))
                .existePorIdItem(idItem);

        verify(anexoGateway, times(1))
                .deletarPorIdItem(idItem);
    }

    // ===========================
    // ❌ ERRO: ANEXO NÃO EXISTE
    // ===========================
    @Test
    void deveLancarExcecaoQuandoIdNaoExistir() {
        // ARRANGE
        String idItem = "999";

        when(anexoGateway.existePorIdItem(idItem))
                .thenReturn(false);

        // ACT + ASSERT
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> deletarAnexoUseCase.executar(idItem)
        );

        assertEquals(
                "Anexo com ID 999 não encontrado.",
                exception.getMessage()
        );

        verify(anexoGateway, times(1))
                .existePorIdItem(idItem);

        verify(anexoGateway, never())
                .deletarPorIdItem(any());
    }
}
