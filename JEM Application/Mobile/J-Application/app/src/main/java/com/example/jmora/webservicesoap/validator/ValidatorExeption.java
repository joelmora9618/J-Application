package com.example.jmora.webservicesoap.validator;

/**
 * Created by JMora on 22/05/2017.
 */

public class ValidatorExeption  extends java.lang.Exception{

    public ValidatorExeption(){
        super();
    }

    public ValidatorExeption(String detailMennsage, Throwable throwable){
        super(detailMennsage,throwable);
    }

    public  ValidatorExeption (String detailMennsage){
        super(detailMennsage);
    }

    public ValidatorExeption(Throwable throwable){
        super(throwable);
    }
}
