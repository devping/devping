package org.jbug.devping.interfaces.common;

/**
 * Created by nadal on 14. 9. 16.
 */

public class RestError {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
