package br.com.exemplo.crudadvogado.core.application.usecase.advogado;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.advogado.AdvogadoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.AdvogadoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Advogado;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.mapper.AdvogadoMapper;
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
class BuscarAdvogadoPorEmailUseCaseTest {

    @Mock
    private AdvogadoGateway advogadoGateway;

    @InjectMocks
    private BuscarAdvogadoPorEmailUseCase buscarAdvogadoPorEmailUseCase;

    private Advogado advogado;

    @BeforeEach
    void setup() {
        advogado = new Advogado();
        advogado.setId(1L);
        advogado.setNome("Anthony");
        advogado.setEmail(Email.criar("anthony@email.com"));
    }

    @Test
    void deveRetornarAdvogadoQuandoEmailExistir() {
        // ✅ ARRANGE - agora o tipo bate com o Gateway
        when(advogadoGateway.buscarPorEmail("anthony@email.com"))
                .thenReturn(Optional.of(advogado));

        // ✅ ACT
        AdvogadoResponse response =
                buscarAdvogadoPorEmailUseCase.executar("anthony@email.com");

        // ✅ ASSERT
        assertNotNull(response);
        assertEquals("Anthony", response.getNome());
        assertEquals("anthony@email.com", response.getEmail());

        verify(advogadoGateway, times(1))
                .buscarPorEmail("anthony@email.com");
    }

    @Test
    void deveLancarExcecaoQuandoEmailNaoExistir() {
        // ✅ ARRANGE
        when(advogadoGateway.buscarPorEmail("inexistente@email.com"))
                .thenReturn(Optional.empty());

        // ✅ ACT + ASSERT
        AdvogadoNaoEncontradoException exception = assertThrows(
                AdvogadoNaoEncontradoException.class,
                () -> buscarAdvogadoPorEmailUseCase.executar("inexistente@email.com")
        );

        assertTrue(exception.getMessage()
                .contains("Advogado não encontrado com email"));

        verify(advogadoGateway, times(1))
                .buscarPorEmail("inexistente@email.com");
    }
}
