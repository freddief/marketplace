package io.freddief.marketplace.exception;

import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
    public ResponseEntity<List<ErrorMessage>> handle(ValidationException ex, WebRequest request) {

        return new ResponseEntity<>(ex.getFailures(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<List<ErrorMessage>> handle(NotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(Lists.newArrayList(new ErrorMessage(ex.getMessage())), HttpStatus.NOT_FOUND);
    }

}
