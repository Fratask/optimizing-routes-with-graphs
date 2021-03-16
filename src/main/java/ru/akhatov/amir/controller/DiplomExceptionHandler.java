package ru.akhatov.amir.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.akhatov.amir.exception.DiplomException;
import ru.akhatov.amir.exception.DiplomResponseCode;

@ControllerAdvice
public class DiplomExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler({DiplomException.class})
    public ResponseEntity<DiplomException> handleDiplomException(DiplomException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                new DiplomException(DiplomResponseCode.UNKNOWN_EXCEPTION.getErrorCode(), ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
