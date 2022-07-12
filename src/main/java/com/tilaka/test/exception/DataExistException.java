package com.tilaka.test.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataExistException extends Exception {
    private int code;
    private String message;

    public DataExistException(int code, String message){
        super();
        this.code = code;
        this.message = message;
    }
}
