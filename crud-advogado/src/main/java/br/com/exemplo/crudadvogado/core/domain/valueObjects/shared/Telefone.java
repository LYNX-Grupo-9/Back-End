package br.com.exemplo.crudadvogado.core.domain.valueObjects.shared;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class Telefone {

    private String numero;
    private String codigoPais;

    private Telefone(String numero, String codigoPais) {
        this.numero = numero;
        this.codigoPais = codigoPais;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public static boolean validarTelefone(String telefone, String codigoPais) {
        PhoneNumberUtil util = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber numero = util.parse(telefone, codigoPais);
            return util.isValidNumber(numero);
        } catch (NumberParseException e) {
            return false;
        }
    }

    public static Telefone criar(String telefone,String codigoPais) {
        if(telefone == null || codigoPais == null) return null;

        if(validarTelefone(telefone, codigoPais)) {
           return new Telefone(telefone, codigoPais);
        }

        throw new IllegalArgumentException("Numero de telefone inválido");
    }
}
