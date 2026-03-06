package alkz.mscurriculum.exception;

import alejdaf.commonutils.dto.ResultDto;
import alejdaf.commonutils.exception.CommonExceptionHandler;
import alejdaf.commonutils.exception.CustomCommonException;
import alejdaf.commonutils.util.enums.ECommonError;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends CommonExceptionHandler {

  /**
   * General exception handler that routes to specific handlers based on exception type.
   * @param exception the Exception to handle
   * @return ResponseEntity containing the ResultDto with error details
   */
  @ExceptionHandler(Exception.class)
  ResponseEntity<ResultDto<Object>> handleGeneralException(Exception exception) {
    log.error(exception.getMessage(), exception);
    return switch (exception) {
      case CustomCommonException customCommonException ->
          this.handleCustomCommonException(customCommonException);
      case HttpMessageNotReadableException httpMessageNotReadableException ->
          this.handleHttpMessageNotReadableException(httpMessageNotReadableException);
      case MethodArgumentNotValidException methodArgumentNotValidException ->
          this.handleMethodArgumentNotValidException(methodArgumentNotValidException);
      case NoResourceFoundException noResourceFoundException ->
          this.handleNoResourceFoundException(noResourceFoundException);
      case ConstraintViolationException constraintViolationException ->
          this.handleConstraintViolationException(constraintViolationException);
      default -> this.handleCustomCommonException(new CustomCommonException());
    };
  }
}
