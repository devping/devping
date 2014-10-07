package org.jbug.devping.domain.social;

/**
 * Created by nadal on 14. 10. 6.
 */
public class DuplicateEmailException extends Exception {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
