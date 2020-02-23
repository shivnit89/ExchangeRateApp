package com.searchmetrics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import java.io.IOException;

/**+
 * This class will be used to handle all the exceptions thrown by the application and convert them to http response
 */
@RestControllerAdvice
public class GlobalExceptionHandler{

    public static final String NO_DATA_FOUND = "NO_DATA_FOUND";

    @ExceptionHandler(ExchangeRateException.class)
    public ResponseEntity<Object> backendServiceNotAvailable(ExchangeRateException ex,WebRequest request) throws IOException {
        ErrorResponse error=new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(),HttpStatus.SERVICE_UNAVAILABLE.name(),ex.getMessage());
        return new ResponseEntity(error,HttpStatus.SERVICE_UNAVAILABLE);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequest(BadRequestException ex, WebRequest request) throws IOException {
        ErrorResponse error=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.name(),ex.getMessage());
        return new ResponseEntity(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> dataNotFound(NoDataFoundException ex, WebRequest request) throws IOException {
        ErrorResponse error=new ErrorResponse(HttpStatus.ACCEPTED.value(), NO_DATA_FOUND,ex.getMessage());
        return new ResponseEntity(error,HttpStatus.ACCEPTED);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> allExceptions(Exception ex, WebRequest request) throws IOException {
        ErrorResponse error=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name(),ex.getMessage());
        return new ResponseEntity(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
