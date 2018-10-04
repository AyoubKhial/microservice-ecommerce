package com.micro.stockservice.exception;

import com.micro.stockservice.util.HTTPCode;
import com.micro.stockservice.util.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestResponse> badRequest(BadRequestException badRequestException) {
        RestResponse response = new RestResponse();
        response.setStatusCode(HTTPCode.BAD_REQUEST);
        response.setMessage(badRequestException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RestResponse> resourceNotFound(ResourceNotFoundException resourceNotFoundException) {
        RestResponse response = new RestResponse();
        response.setStatusCode(HTTPCode.NOT_FOUND);
        response.setMessage(resourceNotFoundException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}