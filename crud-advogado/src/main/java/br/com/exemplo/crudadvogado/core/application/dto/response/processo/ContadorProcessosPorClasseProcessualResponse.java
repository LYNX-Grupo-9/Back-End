package br.com.exemplo.crudadvogado.core.application.dto.response.processo;

import java.util.Map;

public record ContadorProcessosPorClasseProcessualResponse(
        Map<String, Long> contagemPorClasseProcessual
) {
    public Map<Object, Object> getContagemPorClasseProcessual() {
        return Map.of();
    }
}