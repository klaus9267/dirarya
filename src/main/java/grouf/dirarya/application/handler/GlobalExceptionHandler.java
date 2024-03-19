package grouf.dirarya.application.handler;

import grouf.dirarya.application.handler.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<String> handleException(final Exception e) {
    log.error(e.toString());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
  }

  @ExceptionHandler(BadRequestException.class)
  protected ResponseEntity<String> handleBadRequestException(final BadRequestException e) {
    log.error(e.toString());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  protected ResponseEntity<String> handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
    log.error(e.toString());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
  }
}
