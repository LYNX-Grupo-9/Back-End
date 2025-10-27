package br.com.exemplo.crudadvogado.core.domain.valueObjects.parcela;

import java.util.Set;

public class Status {

    private final String valor;

    private Status(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    private static final Set<String> STATUS_VALIDOS =
            Set.of("PAGO", "ATRASO", "PENDENTE");

    private static boolean validarStatus(String valor) {
        if (valor == null) return false;
        return STATUS_VALIDOS.contains(valor.toUpperCase());
    }

    public static Status criar(String valor) {
        if (validarStatus(valor)) {
            return new Status(valor.toUpperCase());
        }
        throw new IllegalArgumentException(
                "Status inválido. Valores aceitos: PAGO, ATRASO ou PENDENTE."
        );
    }
}
