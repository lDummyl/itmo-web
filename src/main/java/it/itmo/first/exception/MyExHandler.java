/*1. Создать Ex handler в своем сервисе. С одним методом отлавливающим Throwable*/

package it.itmo.first.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExHandler extends ResponseEntityExceptionHandler {
    //помогает серверу не падать
    @ExceptionHandler(value = {Throwable.class})
    protected ResponseEntity<Object> handleConflict(Throwable ex, WebRequest request) {
        return handleExceptionInternal(new RuntimeException(), ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


}
