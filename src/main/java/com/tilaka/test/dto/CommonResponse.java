package com.tilaka.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse<T> {
    private int responseCode;
    private String responseMessage;
    private T data;

    public CommonResponse(){
        this.responseCode= 20;
        this.responseMessage= "success";
    }
    public CommonResponse(int responseCode, String responseMessage){
        this.responseCode=responseCode;
        this.responseMessage=responseMessage;
    }
}
