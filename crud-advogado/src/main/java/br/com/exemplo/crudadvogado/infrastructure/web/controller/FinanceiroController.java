package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.response.faturamento.FaturamentoMensalResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.*;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service.FaturamentoService;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service.FinanceiroService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

    private final FinanceiroService financeiroService;
    private final FaturamentoService faturamentoService;

    public FinanceiroController(FinanceiroService financeiroService, FaturamentoService faturamentoService) {
        this.financeiroService = financeiroService;
        this.faturamentoService = faturamentoService;
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

    @GetMapping("/faturamento-6-meses")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<FaturamentoMensalResponse>> getFaturamentoUltimos6Meses() {
        List<FaturamentoMensalResponse> response = faturamentoService.obterFaturamentoUltimos6Meses();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total-faturado-mes")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<TotalFaturadoResponse> getTotalFaturadoMes() {
        TotalFaturadoResponse response = financeiroService.calcularTotalFaturadoMes();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/proximo-pagamento")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ProximoPagamentoResponse> getProximoPagamento() {
        Optional<ProximoPagamentoResponse> response = financeiroService.obterProximoPagamento();

        return response
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}