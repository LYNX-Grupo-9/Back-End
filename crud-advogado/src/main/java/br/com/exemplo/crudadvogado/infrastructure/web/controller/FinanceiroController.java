package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.ClientesComPendenciasResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.ProcessosComPendenciasResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.TotalAReceberResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.TotalPendenteResponse;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service.FinanceiroService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

    private final FinanceiroService financeiroService;

    public FinanceiroController(FinanceiroService financeiroService) {
        this.financeiroService = financeiroService;
    }

    @GetMapping("/total-pendente")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TotalPendenteResponse> getTotalPendenteMes() {
        TotalPendenteResponse response = financeiroService.calcularTotalPendenteMes();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/clientes-com-pendencias")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ClientesComPendenciasResponse> getClientesComPendenciasAtrasadas() {
        ClientesComPendenciasResponse response = financeiroService.calcularClientesComPendenciasAtrasadas();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/processos-com-pendencias")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ProcessosComPendenciasResponse> getProcessosComPendenciasAtrasadas() {
        ProcessosComPendenciasResponse response = financeiroService.calcularProcessosComPendenciasAtrasadas();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total-a-receber")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TotalAReceberResponse> getTotalAReceberProximos30Dias() {
        TotalAReceberResponse response = financeiroService.calcularTotalAReceberProximos30Dias();
        return ResponseEntity.ok(response);
    }
}