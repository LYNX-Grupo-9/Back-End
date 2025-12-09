package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteResponse;
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
class ListarTodosClientesUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private ProcessoGateway processoGateway;

    @InjectMocks
    private ListarTodosClientesUseCase useCase;

    private Cliente cliente;

    @BeforeEach
    void setup() {
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
        cliente.setQtdProcessos(2L);
    }

    // ============================================
    // ✅ SUCESSO — LISTAR TODOS OS CLIENTES
    // ============================================
    @Test
    void deveListarTodosOsClientesComSucesso() {
        // ARRANGE
        when(clienteGateway.listarTodos()).thenReturn(List.of(cliente));
        when(processoGateway.contarProcessosPorCliente(cliente.getIdCliente()))
                .thenReturn(2L);

        // ACT
        List<ClienteResponse> response = useCase.executar();

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

        verify(clienteGateway, times(1)).listarTodos();
        verify(processoGateway, times(1))
                .contarProcessosPorCliente(cliente.getIdCliente());
    }

    // ============================================
    // ✅ LISTA VAZIA
    // ============================================
    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremClientes() {
        // ARRANGE
        when(clienteGateway.listarTodos()).thenReturn(List.of());

        // ACT
        List<ClienteResponse> response = useCase.executar();

        // ASSERT
        assertNotNull(response);
        assertTrue(response.isEmpty());

        verify(clienteGateway, times(1)).listarTodos();
        verify(processoGateway, never()).contarProcessosPorCliente(any());
    }
}
