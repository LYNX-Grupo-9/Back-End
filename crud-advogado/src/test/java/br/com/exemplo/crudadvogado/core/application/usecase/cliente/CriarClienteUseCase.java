package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.cliente.CriarClienteCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.CriarClienteResponse;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.core.domain.Email;
import br.com.exemplo.crudadvogado.core.domain.EstadoCivil;
import br.com.exemplo.crudadvogado.core.domain.Genero;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.EstadoCivil;
import br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Genero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarClienteUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private CriarClienteUseCase criarClienteUseCase;

    private CriarClienteCommand command;
    private Cliente clienteCriado;

    @BeforeEach
    void setup() {
        UUID idCliente = UUID.randomUUID();
        UUID idAdvogado = UUID.randomUUID();

        command = new CriarClienteCommand(
                "Maria Silva",
                "123456789",
                "CPF",
                "maria@email.com",
                "11999998888",
                "Rua A",
                "FEMININO",
                LocalDate.of(1995, 5, 10),
                "SOLTEIRO",
                "Advogada",
                "123456",
                "654321",
                "São Paulo",
                idAdvogado
        );

        clienteCriado = new Cliente();
        clienteCriado.setIdCliente(idCliente);
        clienteCriado.setNome("Maria Silva");
        clienteCriado.setDocumento("123456789");
        clienteCriado.setTipoDocumento("CPF");
        clienteCriado.setEmail(br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Email.criar("joao@email.com"));
        clienteCriado.setTelefone("11999998888");
        clienteCriado.setEndereco("Rua A");
        clienteCriado.setGenero(br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.Genero.criar("Feminino"));
        clienteCriado.setDataNascimento(LocalDate.of(1995, 5, 10));
        clienteCriado.setEstadoCivil(br.com.exemplo.crudadvogado.core.domain.valueObjects.shared.EstadoCivil.criar("Solteiro"));
        clienteCriado.setProfissao("Advogada");
        clienteCriado.setPassaporte("123456");
        clienteCriado.setCnh("654321");
        clienteCriado.setNaturalidade("São Paulo");
        clienteCriado.setQtdProcessos(0L);
        clienteCriado.setIdAdvogado(idAdvogado);
    }

    // ============================================
    // ✅ SUCESSO — CLIENTE CRIADO
    // ============================================
    @Test
    void deveCriarClienteComSucesso() {
        // ARRANGE
        when(clienteGateway.criar(any(Cliente.class)))
                .thenReturn(clienteCriado);

        // ACT
        CriarClienteResponse response =
                criarClienteUseCase.executar(command);

        // ASSERT
        assertNotNull(response);
        assertEquals(clienteCriado.getIdCliente(), response.idCliente());
        assertEquals("Maria Silva", response.nome());
        assertEquals("123456789", response.documento());
        assertEquals("CPF", response.tipoDocumento());
        assertEquals("maria@email.com", response.email());
        assertEquals("11999998888", response.telefone());
        assertEquals("Rua A", response.endereco());
        assertEquals("FEMININO", response.genero());
        assertEquals(LocalDate.of(1995, 5, 10), response.dataNascimento());
        assertEquals("SOLTEIRO", response.estadoCivil());
        assertEquals("Advogada", response.profissao());
        assertEquals("123456", response.passaporte());
        assertEquals("654321", response.cnh());
        assertEquals("São Paulo", response.naturalidade());
        assertEquals(0L, response.qtdProcessos());
        assertEquals(clienteCriado.getIdAdvogado(), response.idAdvogado());

        verify(clienteGateway, times(1))
                .criar(any(Cliente.class));
    }
}
