package me.anant.PMS.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrderNotFoundException extends Exception{

    private String errorMessage;
    public OrderNotFoundException(String message){
        super(message);
        this.errorMessage=message;
    }
}
