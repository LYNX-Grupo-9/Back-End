package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.processo.AtualizarProcessoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.ProcessoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.ProcessoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarProcessoParcialmenteUseCaseTest {

    @Mock
    private ProcessoGateway processoGateway;

    @InjectMocks
    private AtualizarProcessoParcialmenteUseCase useCase;

    private UUID processoId;
    private Processo processo;

    @BeforeEach
    void setup() {
        processoId = UUID.randomUUID();
        processo = mock(Processo.class);
    }

    // ============================================
    // ✅ SUCESSO
    // ============================================
    @Test
    void deveAtualizarProcessoParcialmenteComSucesso() {

        AtualizarProcessoCommand command = new AtualizarProcessoCommand(
                null,
                "123456",
                "Nova descrição",
                "ATIVO",
                "Cível",
                "Cobrança",
                "TJSP",
                "Tribunal",
                new BigDecimal("5000"),
                "Adv Requerente",
                "Réu",
                "Adv Réu",
                "Matheus",
                UUID.randomUUID(),
                null
        );

        when(processoGateway.buscarPorId(processoId))
                .thenReturn(Optional.of(processo));

        when(processoGateway.atualizar(processo))
                .thenReturn(processo);

        ProcessoResponse response = useCase.executar(processoId, command);

        assertNotNull(response);

        verify(processoGateway, times(1)).buscarPorId(processoId);
        verify(processoGateway, times(1)).atualizar(processo);

        verify(processo).setTitulo("Novo Título");
        verify(processo).setNumeroProcesso("123456");
        verify(processo).setDescricao("Nova descrição");
        verify(processo).setStatus("ATIVO");
        verify(processo).setClasseProcessual("Cível");
        verify(processo).setAssunto("Cobrança");
        verify(processo).setTribunal("TJSP");
        verify(processo).setValor(new BigDecimal("5000"));
        verify(processo).setAutor("Autor");
        verify(processo).setAdvRequerente("Adv Requerente");
        verify(processo).setReu("Réu");
        verify(processo).setAdvReu("Adv Réu");
    }

    // ============================================
    // ❌ PROCESSO NÃO ENCONTRADO
    // ============================================
    @Test
    void deveLancarExcecaoQuandoProcessoNaoForEncontrado() {

        AtualizarProcessoCommand command = mock(AtualizarProcessoCommand.class);

        when(processoGateway.buscarPorId(processoId))
                .thenReturn(Optional.empty());

        assertThrows(ProcessoNaoEncontradoException.class, () ->
                useCase.executar(processoId, command)
        );

        verify(processoGateway, times(1)).buscarPorId(processoId);
        verify(processoGateway, never()).atualizar(any());
    }

    // ============================================
    // ✅ ATUALIZAÇÃO PARCIAL (SÓ ALGUNS CAMPOS)
    // ============================================
    @Test
    void deveAtualizarApenasCamposNaoNulos() {

        AtualizarProcessoCommand command = new AtualizarProcessoCommand(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        when(processoGateway.buscarPorId(processoId))
                .thenReturn(Optional.of(processo));

        when(processoGateway.atualizar(processo))
                .thenReturn(processo);

        ProcessoResponse response = useCase.executar(processoId, command);

        assertNotNull(response);

        verify(processo).setTitulo("Título Atualizado");

        verify(processo, never()).setNumeroProcesso(any());
        verify(processo, never()).setDescricao(any());
        verify(processo, never()).setStatus(any());
        verify(processoGateway, times(1)).atualizar(processo);
    }
}
