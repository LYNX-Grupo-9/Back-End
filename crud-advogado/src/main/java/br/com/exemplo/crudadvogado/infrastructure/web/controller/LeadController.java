package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.command.lead.LeadCommand;
import br.com.exemplo.crudadvogado.core.application.dto.response.lead.LeadResponse;
import br.com.exemplo.crudadvogado.core.application.usecase.lead.EnviarLeadUseCase;
import br.com.exemplo.crudadvogado.core.domain.Lead;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final EnviarLeadUseCase enviarLeadUseCase;

    public LeadController(EnviarLeadUseCase enviarLeadUseCase) {
        this.enviarLeadUseCase = enviarLeadUseCase;
    }

    @PostMapping
    public LeadResponse enviarLead(@RequestBody LeadCommand command) {
        return enviarLeadUseCase.executar(command);
    }

}
