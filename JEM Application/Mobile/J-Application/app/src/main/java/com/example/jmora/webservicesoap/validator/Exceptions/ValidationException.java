package com.example.jmora.webservicesoap.validator.Exceptions;

/**
 * Created by JMora on 22/08/2017.
 */

public class ValidationException extends Exception{
    public ValidationException(String msg) {
        super(msg);
    }

    public ValidationException() {
        super();
    }
}
