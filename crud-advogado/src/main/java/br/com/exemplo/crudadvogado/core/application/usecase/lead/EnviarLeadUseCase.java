package br.com.exemplo.crudadvogado.core.application.usecase.lead;

import br.com.exemplo.crudadvogado.core.adapter.gateway.LeadGateway;
import br.com.exemplo.crudadvogado.core.application.dto.command.lead.LeadCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.lead.LeadResponse;
import br.com.exemplo.crudadvogado.core.domain.Lead;

public class EnviarLeadUseCase {

    private final LeadGateway leadGateway;

    public EnviarLeadUseCase(LeadGateway leadGateway) {
        this.leadGateway = leadGateway;
    }

    public LeadResponse executar(LeadCommand command) {
        Lead leadParaRegistrar = Lead.criarNovoLead(
            command.nome(),
            command.telefone(),
            command.email().getEnderacoEmail(),
            command.assunto(),
            command.mensagem(),
            command.advogado()
        );
        Lead leadRegistrado = leadGateway.criar(leadParaRegistrar);

        return new LeadResponse(
            leadRegistrado.getIdLead(),
            leadRegistrado.getNome(),
            leadRegistrado.getTelefone(),
            leadRegistrado.getEmail().getEnderacoEmail(),
            leadRegistrado.getAssunto(),
            leadRegistrado.getMensagem(),
            leadRegistrado.getAdvogado()
        );
    }

}
