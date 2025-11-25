package org.paymentwallet.userservice.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GobalExpection {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String , String>> userNotFoundException(UserNotFoundException ex ){
        log.warn("The User doest not exist {}" , ex.getMessage());
        Map<String , String > errors = new HashMap<>();
        errors.put("message" , " User not exist");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExitsException(EmailAlreadyExistException ex) {
        log.warn("Email address already Exits {}" , ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("message" , "Email address already in use");
        return ResponseEntity.badRequest().body(errors);
    }
}
