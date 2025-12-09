package br.com.exemplo.crudadvogado.core.application.usecase.processo;

import br.com.exemplo.crudadvogado.core.adapter.gateway.ProcessoGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.processo.CriarProcessoCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.processo.CriarProcessoResponse;
import br.com.exemplo.crudadvogado.core.domain.Processo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarProcessoUseCaseTest {

    private ProcessoGateway processoGateway;
    private CriarProcessoUseCase useCase;

    @BeforeEach
    void setUp() {
        processoGateway = mock(ProcessoGateway.class);
        useCase = new CriarProcessoUseCase(processoGateway);
    }

    @Test
    void deveCriarProcessoComSucesso() {
        // dado
        UUID idAdvogado = UUID.randomUUID();
        UUID idCliente = UUID.randomUUID();
        CriarProcessoCommand command = new CriarProcessoCommand(
                "Título do Processo",
                "123456",
                "Descrição do processo",
                "Em Andamento",
                "Cível",
                "Assunto do processo",
                "Tribunal X",
                new BigDecimal("10000"),
                "Autor",
                "Advogado Requerente",
                "Réu",
                "Advogado Réu",
                idAdvogado,
                idCliente
        );

        Processo processoCriado = Processo.criarNovo(
                command.titulo(),
                command.numeroProcesso(),
                command.descricao(),
                command.status(),
                command.classeProcessual(),
                command.assunto(),
                command.tribunal(),
                command.valor(),
                command.autor(),
                command.advRequerente(),
                command.reu(),
                command.advReu(),
                command.idAdvogado(),
                command.idCliente()
        );

        when(processoGateway.criar(any(Processo.class))).thenReturn(processoCriado);

        // quando
        CriarProcessoResponse response = useCase.executar(command);

        // então
        assertNotNull(response);
        assertEquals(processoCriado.getIdProcesso(), response.idProcesso());
        assertEquals(processoCriado.getTitulo(), response.titulo());
        assertEquals(processoCriado.getNumeroProcesso(), response.numeroProcesso());
        assertEquals(processoCriado.getValor().toString(), response.valor());

        verify(processoGateway, times(1)).criar(any(Processo.class));
    }
}
