package grouf.dirarya.application.handler.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BadRequestException extends RuntimeException {
  private final String content;
}
