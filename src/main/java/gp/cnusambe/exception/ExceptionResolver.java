package gp.cnusambe.exception;

import gp.cnusambe.exception.custom.*;
import gp.cnusambe.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionResolver {
    @ExceptionHandler({InvalidPasswordException.class, AccessTokenException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleForbiddenException(HttpServletRequest request, RuntimeException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN.toString(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({UserNotFoundException.class, RefreshTokenException.class, SWNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(HttpServletRequest request, RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.toString(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserIdDuplicatedException.class, SWDuplicatedException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleConflictException(HttpServletRequest request, RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.CONFLICT.toString(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
