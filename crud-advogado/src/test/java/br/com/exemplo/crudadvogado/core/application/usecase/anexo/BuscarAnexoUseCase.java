package br.com.exemplo.crudadvogado.core.application.usecase.anexo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AnexoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.anexo.AnexoResponse;
import br.com.exemplo.crudadvogado.core.domain.Anexo;
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
class BuscarAnexoUseCaseTest {

    @Mock
    private AnexoGateway anexoGateway;

    @InjectMocks
    private BuscarAnexoUseCase buscarAnexoUseCase;

    private UUID idCliente;
    private UUID idProcesso;
    private Anexo anexo;

    @BeforeEach
    void setup() {
        idCliente = UUID.randomUUID();
        idProcesso = UUID.randomUUID();

        anexo = new Anexo();
        anexo.setIdAnexo(null);
        anexo.setNomeAnexo("contrato.pdf");
        anexo.setIdItem(null);
        anexo.setIdCliente(idCliente);
        anexo.setIdProcesso(idProcesso);
    }

    // ===========================
    // ✅ BUSCAR POR ID CLIENTE
    // ===========================
    @Test
    void deveBuscarAnexosPorIdCliente() {
        // ARRANGE
        when(anexoGateway.buscarPorIdCliente(idCliente))
                .thenReturn(List.of(anexo));

        // ACT
        List<AnexoResponse> response =
                buscarAnexoUseCase.buscarPorIdCliente(idCliente);

        // ASSERT
        assertNotNull(response);
        assertEquals(1, response.size());

        AnexoResponse anexoResponse = response.get(0);
        assertEquals(anexo.getIdAnexo(), anexoResponse.idAnexo());
        assertEquals(anexo.getNomeAnexo(), anexoResponse.nomeAnexo());
        assertEquals(anexo.getIdItem(), anexoResponse.idItem());
        assertEquals(anexo.getIdCliente(), anexoResponse.idCliente());
        assertEquals(anexo.getIdProcesso(), anexoResponse.idProcesso());

        verify(anexoGateway, times(1))
                .buscarPorIdCliente(idCliente);
    }

    // ===========================
    // ✅ BUSCAR POR ID PROCESSO
    // ===========================
    @Test
    void deveBuscarAnexosPorIdProcesso() {
        // ARRANGE
        when(anexoGateway.buscarPorIdProcesso(idProcesso))
                .thenReturn(List.of(anexo));

        // ACT
        List<AnexoResponse> response =
                buscarAnexoUseCase.buscarPorIdProcesso(idProcesso);

        // ASSERT
        assertNotNull(response);
        assertEquals(1, response.size());

        AnexoResponse anexoResponse = response.get(0);
        assertEquals(anexo.getIdAnexo(), anexoResponse.idAnexo());
        assertEquals(anexo.getNomeAnexo(), anexoResponse.nomeAnexo());
        assertEquals(anexo.getIdItem(), anexoResponse.idItem());
        assertEquals(anexo.getIdCliente(), anexoResponse.idCliente());
        assertEquals(anexo.getIdProcesso(), anexoResponse.idProcesso());

        verify(anexoGateway, times(1))
                .buscarPorIdProcesso(idProcesso);
    }

    // ===========================
    // ✅ RETORNO VAZIO
    // ===========================
    @Test
    void deveRetornarListaVaziaQuandoNaoHouverAnexos() {
        // ARRANGE
        when(anexoGateway.buscarPorIdCliente(idCliente))
                .thenReturn(List.of());

        // ACT
        List<AnexoResponse> response =
                buscarAnexoUseCase.buscarPorIdCliente(idCliente);

        // ASSERT
        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(anexoGateway, times(1))
                .buscarPorIdCliente(idCliente);
    }
}
