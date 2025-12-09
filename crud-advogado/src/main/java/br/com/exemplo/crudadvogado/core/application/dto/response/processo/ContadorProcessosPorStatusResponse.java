package br.com.exemplo.crudadvogado.core.application.dto.response.processo;

import java.util.Map;

public record ContadorProcessosPorStatusResponse(
        Map<String, Long> contagemPorStatus
) {
    public Map<String, Long> getContagemPorStatus() {
        return Map.of();
    }
}
