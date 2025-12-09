package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.AdvogadoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
import br.com.exemplo.crudadvogado.core.application.exception.AdvogadoNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarClientesPorAdvogadoUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private AdvogadoGateway advogadoGateway;

    @InjectMocks
    private ListarClientesPorAdvogadoUseCase useCase;

    private UUID advogadoId;
    private Cliente cliente;

    @BeforeEach
    void setup() {
        advogadoId = UUID.randomUUID();

        cliente = new Cliente();
        cliente.setIdCliente(UUID.randomUUID());
        cliente.setNome("João");
        cliente.setDocumento("12345678900");
        cliente.setTipoDocumento("CPF");
        cliente.setEmail(br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email.criar("joao@email.com"));
        cliente.setTelefone("11999999999");
        cliente.setEndereco("Rua A");
        cliente.setGenero(br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Genero.criar("MASCULINO"));
        cliente.setDataNascimento(LocalDate.of(1995, 5, 10));
        cliente.setEstadoCivil(br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.EstadoCivil.criar("SOLTEIRO"));
        cliente.setProfissao("Dev");
        cliente.setPassaporte("123");
        cliente.setCnh("456");
        cliente.setNaturalidade("SP");
        cliente.setIdAdvogado(advogadoId);
        cliente.setQtdProcessos(3L);
    }

    // ============================================
    // ✅ SUCESSO — LISTAR CLIENTES POR ADVOGADO
    // ============================================
    @Test
    void deveListarClientesPorAdvogadoComSucesso() {
        // ARRANGE
        when(advogadoGateway.existePorId(advogadoId)).thenReturn(true);
        when(clienteGateway.buscarPorAdvogado(advogadoId))
                .thenReturn(List.of(cliente));

        // ACT
        List<ClienteResponse> response = useCase.executar(advogadoId);

        // ASSERT
        assertNotNull(response);
        assertEquals(1, response.size());

        ClienteResponse clienteResponse = response.get(0);

        assertEquals(cliente.getIdCliente(), clienteResponse.idCliente());
        assertEquals(cliente.getNome(), clienteResponse.nome());
        assertEquals(cliente.getDocumento(), clienteResponse.documento());
        assertEquals(cliente.getTipoDocumento(), clienteResponse.tipoDocumento());
        assertEquals(cliente.getEmail().getEnderacoEmail(), clienteResponse.email());
        assertEquals(cliente.getTelefone(), clienteResponse.telefone());
        assertEquals(cliente.getEndereco(), clienteResponse.endereco());
        assertEquals(cliente.getGenero().getValor(), clienteResponse.genero());
        assertEquals(cliente.getDataNascimento(), clienteResponse.dataNascimento());
        assertEquals(cliente.getEstadoCivil().getValor(), clienteResponse.estadoCivil());
        assertEquals(cliente.getProfissao(), clienteResponse.profissao());
        assertEquals(cliente.getPassaporte(), clienteResponse.passaporte());
        assertEquals(cliente.getCnh(), clienteResponse.cnh());
        assertEquals(cliente.getNaturalidade(), clienteResponse.naturalidade());
        assertEquals(cliente.getIdAdvogado(), clienteResponse.idAdvogado());
        assertEquals(cliente.getQtdProcessos(), clienteResponse.qtdProcessos());

        verify(advogadoGateway, times(1)).existePorId(advogadoId);
        verify(clienteGateway, times(1)).buscarPorAdvogado(advogadoId);
    }

    // ============================================
    // ❌ ADVOGADO NÃO ENCONTRADO
    // ============================================
    @Test
    void deveLancarExcecaoQuandoAdvogadoNaoExistir() {
        // ARRANGE
        when(advogadoGateway.existePorId(advogadoId)).thenReturn(false);

        // ACT + ASSERT
        assertThrows(
                AdvogadoNaoEncontradoException.class,
                () -> useCase.executar(advogadoId)
        );

        verify(advogadoGateway, times(1)).existePorId(advogadoId);
        verify(clienteGateway, never()).buscarPorAdvogado(any());
    }
}
