package com.ral.exception;

/**
 * Created by victor on 2018/1/4.
 */
public class RalShopException extends RuntimeException{

    private String message;

    public RalShopException(){
        super();
    }

    public RalShopException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
