package com.sivthequant.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException exception){
        ErrorResponse error = new ErrorResponse(
                "PRODUCT_NOT_FOUND",
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail notFound(ResourceNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ProblemDetail duplicate(DuplicateResourceException exception){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(ProductExistException.class)
    public ResponseEntity<ErrorResponse> handleProductFound(ProductExistException exception){
        ErrorResponse error = new ErrorResponse(
                "PRODUCT EXISTS",
                exception.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
