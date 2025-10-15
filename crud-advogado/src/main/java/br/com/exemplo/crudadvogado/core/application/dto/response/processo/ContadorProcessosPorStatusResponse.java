package br.com.exemplo.crudadvogado.core.application.dto.response.processo;

import java.util.Map;

public record ContadorProcessosPorStatusResponse(
        Map<String, Long> contagemPorStatus
) {}
