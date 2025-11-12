package br.com.exemplo.crudadvogado.core.application.dto.response.financeiro;

public record ClientesComPendenciasResponse(
        Long totalClientes,
        Double percentual
) {}
