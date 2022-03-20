package com.kaspper.teste.handler;

import com.kaspper.teste.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    public static final String COMMON_DETAIL = "An unexpected internal system error has occurred. Try again and if the problem persists, contact your system administrator.";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = ErrorCustomized.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .detail(ex.getMessage())
                    .build();
        } else if (body instanceof String) {
            body = ErrorCustomized.builder()
                    .title((String) body)
                    .detail(ex.getMessage())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorCustomized errorCustomized = createErrorCustomizedBuilder(status, ErrorCustomizedType.ERROR_SYSTEM, COMMON_DETAIL).build();
        return handleExceptionInternal(ex, errorCustomized, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleUncaught(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorCustomized errorCustomized = createErrorCustomizedBuilder(status, ErrorCustomizedType.ERROR_BUSINESS, ex.getMessage()).build();
        return handleExceptionInternal(ex, errorCustomized, new HttpHeaders(), status, request);
    }

    private ErrorCustomized.ErrorCustomizedBuilder createErrorCustomizedBuilder(HttpStatus status,
                                                                                ErrorCustomizedType errorCustomizedType,
                                                                                String detail) {
        return ErrorCustomized.builder()
                .status(status.value())
                .title(errorCustomizedType.getTitle())
                .detail(detail);
    }
}
