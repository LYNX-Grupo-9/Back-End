package br.com.exemplo.crudadvogado.core.domain.valueObjects.shared;

import java.util.Set;

public class EstadoCivil {

    private final String valor;

    private EstadoCivil(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    private static final Set<String> ESTADOS_VALIDOS =
            Set.of("SOLTEIRO", "CASADO", "DIVORCIADO", "VIÚVO", "OUTRO");

    private static boolean validarEstado(String valor) {
        if (valor == null) return false;
        return ESTADOS_VALIDOS.contains(valor.toUpperCase());
    }

    public static EstadoCivil criar(String valor) {
        if (validarEstado(valor)) {
            return new EstadoCivil(valor.toUpperCase());
        }
        throw new IllegalArgumentException(
                "Estado civil inválido. Valores aceitos: SOLTEIRO, CASADO, DIVORCIADO, VIÚVO ou OUTRO."
        );
    }
}

