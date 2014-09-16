package org.jbug.devping.interfaces.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by nadal on 14. 9. 16.
 * Spring Controller for Rest Api Server.
 */
public class RestApiController {

    private static Logger log = LoggerFactory.getLogger(RestApiController.class);
    /**
     * 잘못된 파라미터입니다.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RestIllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestError exceptionHandler(RestIllegalArgumentException e) {
        log.warn(e.getMessage(), e);
        RestError error = new RestError();
        error.setCode(RestResultType.INVALID_ARGUMENT.name());
        error.setMessage(e.getMessage());
        return error;
    }

    /**
     * 인증을 실패했습니다.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RestUnAuthorizationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public RestError exceptionHandler(RestUnAuthorizationException e) {
        log.warn(e.getMessage(), e);
        RestError error = new RestError();
        error.setCode(RestResultType.UNAUTHORIZATION.name());
        error.setMessage(e.getMessage());
        return error;
    }
}

