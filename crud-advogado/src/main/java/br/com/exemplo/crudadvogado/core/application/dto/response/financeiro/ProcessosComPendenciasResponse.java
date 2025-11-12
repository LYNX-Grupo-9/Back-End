package br.com.exemplo.crudadvogado.core.application.dto.response.financeiro;

public record ProcessosComPendenciasResponse(
        Long totalProcessos,
        Double percentual
) {}