package com.tilaka.test.exception.handler;


import com.tilaka.test.dto.CommonResponse;
import com.tilaka.test.exception.DataExistException;
import com.tilaka.test.exception.UserDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserDataNotFoundException.class)
    public ResponseEntity<?> resp(UserDataNotFoundException e){
        return new ResponseEntity<>(new CommonResponse(e.getCode(), e.getMessage()), HttpStatus.OK);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DataExistException.class)
    public ResponseEntity<?> resp(DataExistException e){
        return new ResponseEntity<>(new CommonResponse(e.getCode(), e.getMessage()), HttpStatus.OK);
    }
}
