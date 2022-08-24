package com.bootcampgp.tiendadeproductos.excepciones;

public class ExisteException extends Exception{

    public ExisteException() {
    }

    public ExisteException(String message) {
        super(message);
    }
}
