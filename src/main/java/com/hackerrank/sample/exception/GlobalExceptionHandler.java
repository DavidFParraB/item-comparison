
package com.hackerrank.sample.exception;

import com.hackerrank.sample.dto.ErrorResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

  @ExceptionHandler(BadResourceRequestException.class)
  public ResponseEntity<ErrorResponseDTO> handleBadResourceRequestException(BadResourceRequestException ex,
      WebRequest request) {
    ErrorResponseDTO responseDTO = ErrorResponseDTO.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .description(ex.getMessage())
        .build();
    return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoSuchResourceFoundException.class)
  public ResponseEntity<ErrorResponseDTO> handleNoSuchResourceFoundException(NoSuchResourceFoundException ex,
      WebRequest request) {
    ErrorResponseDTO responseDTO = ErrorResponseDTO.builder()
        .code(HttpStatus.NOT_FOUND.value())
        .description(ex.getMessage())
        .build();
    return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorResponseDTO> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex,
      WebRequest request) {
    ErrorResponseDTO responseDTO = ErrorResponseDTO.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .description(ex.getMessage())
        .build();
    return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGeneralException(Exception ex) {
    log.error("HandleGeneralException" , ex);
    ErrorResponseDTO responseDTO = ErrorResponseDTO.builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .description(ex.getMessage())
        .build();
    return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
