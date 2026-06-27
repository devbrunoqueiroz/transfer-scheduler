package com.brunoqueiroz.transferencias.exceptions;

public class NoApplicableTaxException extends RuntimeException {

    public NoApplicableTaxException(long days) {
        super("Não existe taxa aplicável para transferências agendadas com " + days + " dias de antecedência.");
    }

}