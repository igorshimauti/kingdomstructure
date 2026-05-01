package br.com.videira.dynamis.kingdomstructure.exception.handler;

import br.com.videira.dynamis.kingdomstructure.dto.response.ErrorResponse;
import br.com.videira.dynamis.kingdomstructure.exception.ResourceNotFoundException;
import br.com.videira.dynamis.kingdomstructure.exception.UserEmailAlreadyExistsException;
import br.com.videira.dynamis.kingdomstructure.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserEmailAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException ex) {
        var error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({ResourceNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex) {
        var error = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadCredentialsException.class, InternalAuthenticationServiceException.class})
    public ResponseEntity<ErrorResponse> handleBadCredentialsException() {
        var error = new ErrorResponse("Bad credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(RuntimeException ex) {
        var error = new ErrorResponse(ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}