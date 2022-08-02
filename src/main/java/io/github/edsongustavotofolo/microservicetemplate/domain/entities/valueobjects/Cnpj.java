package io.github.edsongustavotofolo.microservicetemplate.domain.entities.valueobjects;

import lombok.EqualsAndHashCode;

import java.util.InputMismatchException;

@EqualsAndHashCode
public final class Cnpj {
    private final String numero;

    public Cnpj(final String numero) {
        if (numero == null || numero.isEmpty() ) {
            throw new IllegalArgumentException("CNPJ não informado");
        }
        if (numeroInvalido(numero)) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        this.numero = numero;
    }

    private boolean numeroInvalido(final String numero) {
        var cnpj = numero.replaceAll("\\D", "");

        if (!cnpj.matches("\\d{14}")) {
            return true;
        }
        if (!digitoVerificadorValido(cnpj)) {
            return true;
        }

        return false;
    }

    private boolean digitoVerificadorValido(final String numero) {
        if (numero.equals("00000000000000") || numero.equals("11111111111111")
                || numero.equals("22222222222222") || numero.equals("33333333333333")
                || numero.equals("44444444444444") || numero.equals("55555555555555")
                || numero.equals("66666666666666") || numero.equals("77777777777777")
                || numero.equals("88888888888888") || numero.equals("99999999999999"))
            return false;

        char dig13, dig14;
        int sm, i, r, num, peso;
        try {
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = numero.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else
                dig13 = (char) ((11 - r) + 48);

            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = numero.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }
            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else
                dig14 = (char) ((11 - r) + 48);

            return (dig13 == numero.charAt(12)) && (dig14 == numero.charAt(13));
        } catch (InputMismatchException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.numero;
    }
}
