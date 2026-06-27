package com.brunoqueiroz.transferencias.exceptions;

public class TransferNotFoundException extends RuntimeException {

    public TransferNotFoundException(Long id) {
        super("Transferência com ID " + id + " não encontrada.");
    }

}