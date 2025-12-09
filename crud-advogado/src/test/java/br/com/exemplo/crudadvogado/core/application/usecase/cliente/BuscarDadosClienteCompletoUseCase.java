package br.com.exemplo.crudadvogado.core.application.usecase.cliente;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ClienteGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.EventoGateway;
import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.response.cliente.ClienteProcessoEventoResponse;
import br.com.exemplo.crudadvogado.core.application.exception.ClienteNaoEncontradoException;
import br.com.exemplo.crudadvogado.core.domain.Cliente;
import br.com.exemplo.crudadvogado.core.domain.Evento;
import br.com.exemplo.crudadvogado.core.domain.Processo;
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
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarDadosClienteCompletoUseCaseTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private ProcessoGateway processoGateway;

    @Mock
    private EventoGateway eventoGateway;

    @InjectMocks
    private BuscarDadosClienteCompletoUseCase buscarDadosClienteCompletoUseCase;

    private Cliente cliente;
    private Processo processo;
    private Evento evento;

    private UUID idCliente;
    private UUID idProcesso;
    private UUID idEvento;

    @BeforeEach
    void setup() {
        idCliente = UUID.randomUUID();
        idProcesso = UUID.randomUUID();
        idEvento = UUID.randomUUID();

        cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        cliente.setNome("João da Silva");
        cliente.setDocumento("123456789");
        cliente.setTipoDocumento("CPF");
        cliente.setEmail(Email.criar("joao@email.com"));
        cliente.setTelefone("11999999999");
        cliente.setEndereco("Rua A");
        cliente.setGenero(Genero.criar("M"));
        cliente.setDataNascimento(LocalDate.of(1995, 5, 10));
        cliente.setEstadoCivil(EstadoCivil.criar("Solteiro"));
        cliente.setProfissao("Engenheiro");
        cliente.setPassaporte("P123");
        cliente.setCnh("123456");
        cliente.setNaturalidade("SP");

        cliente.setProcessos(List.of(idProcesso));
        cliente.setEventos(List.of());

        processo = new Processo();
        processo.setNumeroProcesso("0001234-56.2024.8.26.0100");

        evento = new Evento();
        evento.setIdEvento(idEvento);
        evento.setDataReuniao(LocalDate.of(2025, 1, 10));
        evento.setHoraInicio(LocalTime.of(10, 0));
        evento.setHoraFim(LocalTime.of(11, 0));
        evento.setNome("Reunião Inicial");
        evento.setCategoria(Long.valueOf("Audiência"));
    }

    // ==================================================
    // ✅ SUCESSO — BUSCA COMPLETA DO CLIENTE
    // ==================================================
    @Test
    void deveBuscarDadosCompletosDoClienteComSucesso() {
        // ARRANGE
        when(clienteGateway.buscarPorId(idCliente))
                .thenReturn(Optional.of(cliente));

        when(processoGateway.buscarPorId(idProcesso))
                .thenReturn(Optional.of(processo));

        when(eventoGateway.buscarPorId(idEvento))
                .thenReturn(Optional.of(evento));

        // ACT
        ClienteProcessoEventoResponse response =
                buscarDadosClienteCompletoUseCase.executar(idCliente);

        // ASSERT
        assertNotNull(response);
        assertEquals(idCliente, response.idCliente());
        assertEquals("João da Silva", response.nome());
        assertEquals("joao@email.com", response.email());

        // Valida processo
        assertEquals(1, response.processos().size());
        assertEquals(
                "0001234-56.2024.8.26.0100",
                response.processos().get(0).numeroProcesso()
        );

        // Valida evento
        assertEquals(1, response.eventos().size());
        assertEquals("Reunião Inicial", response.eventos().get(0).nome());

        verify(clienteGateway, times(1))
                .buscarPorId(idCliente);

        verify(processoGateway, times(1))
                .buscarPorId(idProcesso);

        verify(eventoGateway, times(1))
                .buscarPorId(idEvento);
    }

    // ==================================================
    // ❌ ERRO — CLIENTE NÃO ENCONTRADO
    // ==================================================
    @Test
    void deveLancarExcecaoQuandoClienteNaoForEncontrado() {
        // ARRANGE
        when(clienteGateway.buscarPorId(idCliente))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        ClienteNaoEncontradoException exception =
                assertThrows(
                        ClienteNaoEncontradoException.class,
                        () -> buscarDadosClienteCompletoUseCase.executar(idCliente)
                );

        assertEquals("Cliente não encontrado", exception.getMessage());

        verify(clienteGateway, times(1))
                .buscarPorId(idCliente);

        verifyNoInteractions(processoGateway);
        verifyNoInteractions(eventoGateway);
    }
}
