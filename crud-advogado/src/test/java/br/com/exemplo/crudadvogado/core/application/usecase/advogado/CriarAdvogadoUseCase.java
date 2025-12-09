package br.com.exemplo.crudadvogado.core.application.usecase.advogado;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.advogado.CriarAdvogadoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.advogado.CriarAdvogadoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.DuplicidadeException;
import br.com.exemplo.crudadvogado.core.domain.Advogado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarAdvogadoUseCaseTest {

    @Mock
    private AdvogadoGateway advogadoGateway;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CriarAdvogadoUseCase criarAdvogadoUseCase;

    private CriarAdvogadoCommand command;

    @BeforeEach
    void setup() {
        command = new CriarAdvogadoCommand(
                "Anthony",
                "123456-SP",
                "12345678900",
                "anthony@email.com",
                "123456"
        );
    }

    // =========================
    // ✅ CENÁRIO DE SUCESSO
    // =========================
    @Test
    void deveCriarAdvogadoComSucesso() {
        // ARRANGE
        when(passwordEncoder.encode("123456"))
                .thenReturn("senha-criptografada");

        when(advogadoGateway.existePorEmail("anthony@email.com"))
                .thenReturn(false);

        when(advogadoGateway.existePorCpf("12345678900"))
                .thenReturn(false);

        when(advogadoGateway.existePorOab("123456-SP"))
                .thenReturn(false);

        Advogado advogadoSalvo = Advogado.criarNovo(
                "Anthony",
                "123456-SP",
                "12345678900",
                "anthony@email.com",
                "senha-criptografada"
        );
        advogadoSalvo.setIdAdvogado(null);

        when(advogadoGateway.criar(any(Advogado.class)))
                .thenReturn(advogadoSalvo);

        // ACT
        CriarAdvogadoResponse response =
                criarAdvogadoUseCase.excutar(command);

        // ASSERT
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Anthony", response.nome());
        assertEquals("anthony@email.com", response.email());
        assertEquals("12345678900", response.cpf());
        assertEquals("123456-SP", response.oab());

        verify(passwordEncoder, times(1))
                .encode("123456");

        verify(advogadoGateway, times(1))
                .criar(any(Advogado.class));
    }

    // =========================
    // ❌ DUPLICIDADE DE EMAIL
    // =========================
    @Test
    void deveLancarExcecaoQuandoEmailJaExistir() {
        // ARRANGE
        when(advogadoGateway.existePorEmail(command.email()))
                .thenReturn(true);

        // ACT + ASSERT
        DuplicidadeException exception = assertThrows(
                DuplicidadeException.class,
                () -> criarAdvogadoUseCase.excutar(command)
        );

        assertEquals("Email já cadastrado", exception.getMessage());

        verify(advogadoGateway, never()).criar(any());
    }

    // =========================
    // ❌ DUPLICIDADE DE CPF
    // =========================
    @Test
    void deveLancarExcecaoQuandoCpfJaExistir() {
        // ARRANGE
        when(advogadoGateway.existePorEmail(command.email()))
                .thenReturn(false);

        when(advogadoGateway.existePorCpf(command.cpf()))
                .thenReturn(true);

        // ACT + ASSERT
        DuplicidadeException exception = assertThrows(
                DuplicidadeException.class,
                () -> criarAdvogadoUseCase.excutar(command)
        );

        assertEquals("CPF já cadastrado", exception.getMessage());

        verify(advogadoGateway, never()).criar(any());
    }

    // =========================
    // ❌ DUPLICIDADE DE OAB
    // =========================
    @Test
    void deveLancarExcecaoQuandoOabJaExistir() {
        // ARRANGE
        when(advogadoGateway.existePorEmail(command.email()))
                .thenReturn(false);

        when(advogadoGateway.existePorCpf(command.cpf()))
                .thenReturn(false);

        when(advogadoGateway.existePorOab(command.oab()))
                .thenReturn(true);

        // ACT + ASSERT
        DuplicidadeException exception = assertThrows(
                DuplicidadeException.class,
                () -> criarAdvogadoUseCase.excutar(command)
        );

        assertEquals("OAB já cadastrado", exception.getMessage());

        verify(advogadoGateway, never()).criar(any());
    }
}
