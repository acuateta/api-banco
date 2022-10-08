package com.acuateta.banco.exception;

public class SaldoInsuficienteException extends Exception{

    public SaldoInsuficienteException(String errorMessage) {
        super(errorMessage);
    }
}
