package it.itmo.first.exceptions;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class UpdateException extends RuntimeException {
    public UpdateException(String message) {
        super(message);
    }
}
