package com.spring.service.transactioncodechallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@ResponseBody
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountBalanceCeroException.class)
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ExceptionHandler(BadParamsNewTransactionException.class)
    public void exceptionHandleBadParams(HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value());
    }

    @ExceptionHandler(DuplicateTransactionReferenceException.class)
    public void exceptionHandleBodyRequestBadParams(HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value());
    }


}
