package me.anant.PMS.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProductNotFoundException extends Exception {

    private String errorMessage;

    public ProductNotFoundException(String message){
        super(message);
        this.errorMessage = message;
    }

}
