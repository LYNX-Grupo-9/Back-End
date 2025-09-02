package br.com.exemplo.crudadvogado.core.domain.valueObjects.shared;

import java.util.Set;

public class Genero {

    private final String valor;

    private Genero(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    private static final Set<String> GENEROS_VALIDOS = Set.of("MASCULINO", "FEMININO", "OUTRO");

    private static boolean validarGenero(String valor) {
        if (valor == null) return false;
        return GENEROS_VALIDOS.contains(valor.toUpperCase());
    }

    public static Genero criar(String valor) {
        if (validarGenero(valor)) {
            return new Genero(valor.toUpperCase());
        }
        throw new IllegalArgumentException("Gênero inválido. Valores aceitos: MASCULINO, FEMININO ou OUTRO.");
    }
}

