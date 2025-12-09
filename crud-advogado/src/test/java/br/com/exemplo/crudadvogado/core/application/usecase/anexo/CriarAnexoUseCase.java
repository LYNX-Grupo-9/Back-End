package br.com.exemplo.crudadvogado.core.application.usecase.anexo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AnexoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.anexo.CriarAnexoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.anexo.CriarAnexoResponse;
import br.com.exemplo.crudadvogado.core.domain.Anexo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarAnexoUseCaseTest {

    @Mock
    private AnexoGateway anexoGateway;

    @InjectMocks
    private CriarAnexoUseCase criarAnexoUseCase;

    private CriarAnexoCommand commandComCliente;
    private CriarAnexoCommand commandComProcesso;

    private UUID idCliente;
    private UUID idProcesso;
    private UUID idItem;

    @BeforeEach
    void setup() {
        idCliente = UUID.randomUUID();
        idProcesso = UUID.randomUUID();
        idItem = UUID.randomUUID();

        commandComCliente = new CriarAnexoCommand(
                "arquivo.pdf",
                "",
                idItem,
                idCliente

        );

        commandComProcesso = new CriarAnexoCommand(
                "arquivo.pdf",
                "",
                idItem,
                idCliente
        );
    }

    // ===========================
    // ✅ SUCESSO COM ID CLIENTE
    // ===========================
    @Test
    void deveCriarAnexoComIdCliente() {
        // ARRANGE
        Anexo anexoCriado = Anexo.criarNovo(
                "arquivo.pdf",
                "",
                idItem,
                idCliente
        );
        anexoCriado.setIdAnexo(null);

        when(anexoGateway.criar(any(Anexo.class)))
                .thenReturn(anexoCriado);

        // ACT
        CriarAnexoResponse response =
                criarAnexoUseCase.executar(commandComCliente);

        // ASSERT
        assertNotNull(response);
        assertEquals(anexoCriado.getIdAnexo(), response.idAnexo());
        assertEquals("arquivo.pdf", response.nomeAnexo());
        assertEquals(idItem, response.idItem());
        assertEquals(idCliente, response.idCliente());
        assertNull(response.idProcesso());

        verify(anexoGateway, times(1))
                .criar(any(Anexo.class));
    }

    // ===========================
    // ✅ SUCESSO COM ID PROCESSO
    // ===========================
    @Test
    void deveCriarAnexoComIdProcesso() {
        // ARRANGE
        Anexo anexoCriado = Anexo.criarNovo(
                "arquivo.pdf",
                "",
                idItem,
                idCliente
        );
        anexoCriado.setIdAnexo(null);

        when(anexoGateway.criar(any(Anexo.class)))
                .thenReturn(anexoCriado);

        // ACT
        CriarAnexoResponse response =
                criarAnexoUseCase.executar(commandComProcesso);

        // ASSERT
        assertNotNull(response);
        assertEquals(anexoCriado.getIdAnexo(), response.idAnexo());
        assertEquals("contrato.pdf", response.nomeAnexo());
        assertEquals(idItem, response.idItem());
        assertNull(response.idCliente());
        assertEquals(idProcesso, response.idProcesso());

        verify(anexoGateway, times(1))
                .criar(any(Anexo.class));
    }

    // ===========================
    // ❌ ERRO: NENHUM ID INFORMADO
    // ===========================
    @Test
    void deveLancarErroQuandoNenhumIdForInformado() {
        // ARRANGE
        CriarAnexoCommand commandInvalido = new CriarAnexoCommand(
                "arquivo.pdf",
                "",
                idItem,
                idCliente
        );

        // ACT + ASSERT
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> criarAnexoUseCase.executar(commandInvalido)
        );

        assertEquals("Deve informar idCliente ou idProcesso", exception.getMessage());

        verify(anexoGateway, never()).criar(any());
    }

    // ===========================
    // ❌ ERRO: DOIS IDS INFORMADOS
    // ===========================
    @Test
    void deveLancarErroQuandoAmbosIdsForemInformados() {
        // ARRANGE
        CriarAnexoCommand commandInvalido = new CriarAnexoCommand(
                "arquivo.pdf",
                "",
                idItem,
                idCliente
        );

        // ACT + ASSERT
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> criarAnexoUseCase.executar(commandInvalido)
        );

        assertEquals(
                "Informe apenas idCliente ou idProcesso, não ambos",
                exception.getMessage()
        );

        verify(anexoGateway, never()).criar(any());
    }
}
