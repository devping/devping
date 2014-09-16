package org.jbug.devping.interfaces.common;


/**
 * api parameter가 잘못된 경우 발생하는 예외.
 */
public class RestIllegalArgumentException extends IllegalArgumentException {

    public RestIllegalArgumentException(String message) {
        super(message);
    }
}


