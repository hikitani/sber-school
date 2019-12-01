package com.sbt.javaschool.rnd.lesson9.cache.exceptions;

import java.util.List;

public class InvalidSignatureException extends Exception{

    private Class[] foundSignature;
    private Class[] expectedSignature;

    public InvalidSignatureException() {
        super();
    }

    public InvalidSignatureException(String msg) {
        super(msg);
    }

    public  InvalidSignatureException(String msg, Class[] foundSignature, Class[] exceptedSignature) {
        super(msg);
        this.foundSignature = foundSignature;
        this.expectedSignature = exceptedSignature;
    }

    public Class[] getFoundSignature() {
        return foundSignature;
    }

    public Class[] getExpectedSignature() {
        return expectedSignature;
    }
}
