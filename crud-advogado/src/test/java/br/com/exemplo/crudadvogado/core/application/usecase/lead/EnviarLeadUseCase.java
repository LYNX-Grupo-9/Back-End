package br.com.exemplo.crudadvogado.core.application.usecase.lead;

import br.com.exemplo.crudadvogado.core.adapter.gateway.LeadGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.lead.LeadCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.lead.LeadResponse;
import br.com.exemplo.crudadvogado.core.domain.Lead;
import br.com.exemplo.crudadvogado.core.domain.Advogado;
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
class EnviarLeadUseCaseTest {

    @Mock
    private LeadGateway leadGateway;

    @InjectMocks
    private EnviarLeadUseCase useCase;

    private UUID idLead;
    private LeadCommand command;
    private Lead leadCriado;

    private Advogado advogado;
    private String email;

    @BeforeEach
    void setup() {
        idLead = UUID.randomUUID();

        email = ("lead@email.com");
        advogado = new Advogado(); // ajuste se seu construtor for diferente

        command = new LeadCommand(
                "João da Silva",
                "11999999999",
                email,
                "Orçamento",
                "Gostaria de saber valores.",
                null
        );

        leadCriado = mock(Lead.class);

        when(leadCriado.getIdLead()).thenReturn(null);
        when(leadCriado.getNome()).thenReturn("João da Silva");
        when(leadCriado.getTelefone()).thenReturn("11999999999");
        when(leadCriado.getEmail()).thenReturn(null);
        when(leadCriado.getAssunto()).thenReturn("Orçamento");
        when(leadCriado.getMensagem()).thenReturn("Gostaria de saber valores.");
        when(leadCriado.getAdvogado()).thenReturn(null);
    }

    // ============================================
    // ✅ SUCESSO
    // ============================================
    @Test
    void deveEnviarLeadComSucesso() {
        // ARRANGE
        when(leadGateway.criar(any(Lead.class)))
                .thenReturn(leadCriado);

        // ACT
        LeadResponse response = useCase.executar(command);

        // ASSERT
        assertNotNull(response);
        assertEquals(idLead, response.idLead());
        assertEquals("João da Silva", response.nome());
        assertEquals("11999999999", response.telefone());
        assertEquals("lead@email.com", response.email());
        assertEquals("Orçamento", response.assunto());
        assertEquals("Gostaria de saber valores.", response.mensagem());
        assertEquals(advogado, response.advogado());

        verify(leadGateway, times(1))
                .criar(any(Lead.class));
    }
}
