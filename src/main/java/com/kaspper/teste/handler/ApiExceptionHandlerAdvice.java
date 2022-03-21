package com.kaspper.teste.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.kaspper.teste.exception.BusinessException;
import com.kaspper.teste.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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
        ex.printStackTrace();
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

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleUncaught(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorCustomized errorCustomized = createErrorCustomizedBuilder(status, ErrorCustomizedType.ERROR_BUSINESS, ex.getMessage()).build();
        return handleExceptionInternal(ex, errorCustomized, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String detail = "One or more fields are invalid. Please fill in correctly and try again.";

        BindingResult bindingResult = ex.getBindingResult();

        List<ErrorCustomized.Field> fields = bindingResult.getFieldErrors().stream().map(fieldError -> {
            return ErrorCustomized.Field.builder()
                    .name(fieldError.getField())
                    .fieldMessage(fieldError.getDefaultMessage())
                    .build();
        }).collect(Collectors.toList());

        ErrorCustomized errorCustomized = createErrorCustomizedBuilder(status, ErrorCustomizedType.INVALID_DATA, detail)
                .fields(fields)
                .build();
        return handleExceptionInternal(ex, errorCustomized, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String detail = String.format("Property '%s' is wrong or does not exist. Correct or remove this property and try again.", ex.getFieldError().getField());
        ErrorCustomized errorCustomized = createErrorCustomizedBuilder(status, ErrorCustomizedType.INVALID_DATA, detail)
                .build();
        return handleExceptionInternal(ex, errorCustomized, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ex.getRootCause();

        if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        String detail = "The request body is invalid. Check syntax error.";
        ErrorCustomized problem = createErrorCustomizedBuilder(status, ErrorCustomizedType.INVALID_DATA, detail)
                .build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());
        String detail = String.format("Property '%s' does not exist. Correct or remove this property and try again.", path);
        ErrorCustomized errorCustomized = createErrorCustomizedBuilder(status, ErrorCustomizedType.INVALID_DATA, detail)
                .build();
        return handleExceptionInternal(ex, errorCustomized, headers, status, request);
    }

    private ErrorCustomized.ErrorCustomizedBuilder createErrorCustomizedBuilder(HttpStatus status,
                                                                                ErrorCustomizedType errorCustomizedType,
                                                                                String detail) {
        return ErrorCustomized.builder()
                .status(status.value())
                .title(errorCustomizedType.getTitle())
                .detail(detail);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }
}
