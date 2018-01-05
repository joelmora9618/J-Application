package com.example.jmora.webservicesoap.validator.Exceptions;

/**
 * Created by JMora on 08/08/2017.
 */

public class ServiceErrorException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = -5899498022474030999L;

    public ServiceErrorException(String msg) {
        super(msg);
    }

    public ServiceErrorException() {
        super();
    }
}
