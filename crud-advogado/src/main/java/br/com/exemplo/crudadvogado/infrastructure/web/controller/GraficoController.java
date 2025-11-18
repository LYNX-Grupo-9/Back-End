package br.com.exemplo.crudadvogado.infrastructure.web.controller;

import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.FaturadoUltimos6MesesResponse;
import br.com.exemplo.crudadvogado.core.application.dto.response.financeiro.PendenteUltimos6MesesResponse;
import br.com.exemplo.crudadvogado.infrastructure.persistence.jpa.adapter.service.GraficoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grafico")
public class GraficoController {

    private final GraficoService graficoService;

    public GraficoController(GraficoService graficoService) {
        this.graficoService = graficoService;
    }

    @GetMapping("/faturado-ultimos-6-meses")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FaturadoUltimos6MesesResponse> getFaturadoUltimos6Meses() {
        FaturadoUltimos6MesesResponse response = graficoService.obterFaturadoUltimos6Meses();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pendente-ultimos-6-meses")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<PendenteUltimos6MesesResponse> getPendenteUltimos6Meses() {
        PendenteUltimos6MesesResponse response = graficoService.obterPendenteUltimos6Meses();
        return ResponseEntity.ok(response);
    }
}
