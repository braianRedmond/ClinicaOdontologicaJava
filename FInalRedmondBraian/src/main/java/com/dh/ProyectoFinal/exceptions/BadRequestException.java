package com.dh.ProyectoFinal.exceptions;

public class BadRequestException extends Exception{
    public BadRequestException(String mensaje){
        super(mensaje);
    }
}

