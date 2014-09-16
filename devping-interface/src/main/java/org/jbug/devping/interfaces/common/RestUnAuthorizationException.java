package org.jbug.devping.interfaces.common;

/**
 * Created by nadal on 14. 9. 16.
 */
public class RestUnAuthorizationException extends RuntimeException {
    public RestUnAuthorizationException(String message) {
        super(message);
    }
}
