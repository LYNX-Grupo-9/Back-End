package br.com.exemplo.crudadvogado.core.domain.valueObjects.advogado;

import java.util.Set;

public class Oab {
    private String numeroOab;

    private Oab(String numeroOab) {
        this.numeroOab = numeroOab;
    }

    public String getNumeroOab() {
        return numeroOab;
    }

    public void setNumeroOab(String numeroOab) {
        this.numeroOab = numeroOab;
    }

    private static final Set<String> UFS_VALIDAS = Set.of(
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
            "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
            "RS", "RO", "RR", "SC", "SP", "SE", "TO"
    );


    public static Boolean validarOab(String numeroOab) {
        if (numeroOab == null || numeroOab.isEmpty()) {
            return false;
        }

        String regex = "^([A-Z]{2})(\\d{1,6})$";

        if (!numeroOab.matches(regex)) {
            return false;
        }

        String uf = numeroOab.substring(0, 2);

        return UFS_VALIDAS.contains(uf);
    }

    public static Oab criar(String numeroOab) {
        if (validarOab(numeroOab)) {
            return new Oab(numeroOab);
        }
        throw new IllegalArgumentException("Número da OAB inválido");
    }

}
